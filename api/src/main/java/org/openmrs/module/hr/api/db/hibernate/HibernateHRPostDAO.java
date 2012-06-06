package org.openmrs.module.hr.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.Person;
import org.openmrs.api.ConceptService;
import org.openmrs.api.LocationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.api.db.HRPostDAO;

import java.sql.Timestamp;
import java.util.*;

public class HibernateHRPostDAO implements HRPostDAO {
    protected Log log = LogFactory.getLog(this.getClass());

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    public HrPost savePost(HrPost post) {
            log.debug("saving HrPost instance");
            try {
                if(post.getId()!=null && post.getId()!=0)
                    sessionFactory.getCurrentSession().merge(post);
                else
                sessionFactory.getCurrentSession().saveOrUpdate(post);
                log.debug("save successful");
                return post;
            }
            catch (RuntimeException re) {
                log.error("save failed", re);
                throw re;
            }

        }
    public void deletePost(HrPost post) {
            log.debug("deleting HrPost instance");
            try {
                sessionFactory.getCurrentSession().delete(post);
                log.debug("delete successful");
            }
            catch (RuntimeException re) {
                log.error("delete failed", re);
                throw re;
            }
        }
        public List<HrPost> findPostByExample(HrPost post) {
            log.debug("finding post by example");
            try {
                List<HrPost> results = sessionFactory.getCurrentSession().createCriteria(HrPost.class).add(Example.create(post)).list();
                if(results!=null)
                log.debug("find by example successful, result size: " + results.size());
                else
                    results=new ArrayList<HrPost>();
                return results;
            }
            catch (RuntimeException re) {
                log.error("find by example failed", re);
                throw re;
            }
        }
        public List<HrPost> getAllPosts(boolean includeAllPosts,boolean includeAllLocations) {
            log.debug("getting all Posts");
               List<HrPost> postList=null;
            Criteria criteria=sessionFactory.getCurrentSession().createCriteria(HrPost.class).createAlias("hrJobTitle","jobTitle").addOrder(Order.asc("jobTitle.title"));
            LocationService locService= Context.getLocationService();
            ConceptService conceptService=Context.getConceptService();
            List<Location> hrManagedLocations=locService.getLocationsByTag(locService.getLocationTagByName("HR Managed"));
            List<Location> otherLocationList=locService.getAllLocations();
            otherLocationList.removeAll(hrManagedLocations);
            List<Concept> postStatusCurrent=conceptService.getConceptsByMapping("Post status current", "HR Module");
            try {
                if(!includeAllPosts)
                    criteria.add(Restrictions.in("status", postStatusCurrent));
                   if(!includeAllLocations){
                       if(!hrManagedLocations.isEmpty())
                       criteria.add(Restrictions.in("location",hrManagedLocations));
                       else
                       criteria.add(Restrictions.not(Restrictions.in("location",otherLocationList)));
                   }
                   postList=criteria.list();
                if (postList==null) {
                    postList=new ArrayList<HrPost>();
                    log.debug("get successful, no posts found");
                }
                else {
                    log.debug("get successful, returning posts found");
                }
                return postList;
            }

            catch (RuntimeException re) {
                log.error("get failed", re);
                throw re;
            }
        }
        public HrPost getPostById(int id) {
            log.debug("getting HrPost instance with id: " + id);
            try {
                HrPost instance = (HrPost) sessionFactory.getCurrentSession().get(HrPost.class, id);
                if (instance==null) {
                    log.debug("get successful, no instance found");
                }
                else {
                    log.debug("get successful, instance found");
                }
                return instance;
            }
            catch (RuntimeException re) {
                log.error("get failed", re);
                throw re;
            }
        }
    public String getMostRecentIncumbentForPostbyId(int id){
		List results=sessionFactory.getCurrentSession().createCriteria(HrPost.class).add(Restrictions.eq("postId", id)).createAlias("hrPostHistories", "ph1").setProjection(Projections.max("ph1.startDate")).list();
		if(results.get(0)!=null){
			Date maxDate=new Date(((Timestamp)results.get(0)).getTime());
			List rowList=sessionFactory.getCurrentSession().createCriteria(HrPost.class).add(Restrictions.eq("postId", id)).createAlias("hrPostHistories", "ph2").add(Restrictions.eq("ph2.startDate", maxDate)).createAlias("ph2.hrStaff", "staff").setProjection(Projections.projectionList().add(Projections.property("staff.staffId"),"staffId").add(Projections.property("ph2.endDate"),"endDate")).list();
			if(rowList!=null){
			Object[] result = (Object[]) rowList.get(0);
			int staffId = (Integer)result[0];
			Date endDate=null;
			if(result[1]!=null)
			endDate= new Date(((Timestamp)result[1]).getTime());
			Person person=Context.getPersonService().getPerson(staffId);
			String personName;
			if(endDate!=null){

				personName="["+person.getGivenName()+" "+person.getFamilyName()+"]";
			}
			else
				personName=person.getGivenName()+" "+person.getFamilyName();
			return personName;
		}
		}
		return null;

	}

    public Map<String, Object> getCurrentJobLocationForStaff(int id) {
		Map<String,Object> jlMap=new HashMap<String, Object>();;
		List results=sessionFactory.getCurrentSession().createCriteria(HrStaff.class).add(Restrictions.eq("staffId", id)).createAlias("hrPostHistories", "ph1").setProjection(Projections.max("ph1.startDate")).list();
		if(results.get(0)!=null){
			Date maxDate=new Date(((Timestamp)results.get(0)).getTime());
			List rowList=sessionFactory.getCurrentSession().createCriteria(HrStaff.class).add(Restrictions.eq("staffId", id)).createAlias("hrPostHistories", "ph2").add(Restrictions.eq("ph2.startDate", maxDate)).setProjection(Projections.projectionList().add(Projections.property("ph2.hrPost.postId"),"postId")).list();
			if(rowList!=null){
			int postId = (Integer) rowList.get(0);
			List details=sessionFactory.getCurrentSession().createCriteria(HrPost.class).add(Restrictions.eq("postId", postId)).setProjection(Projections.projectionList().add(Projections.property("hrJobTitle")).add(Projections.property("location"))).list();
			if(details!=null){
			Object[] result = (Object[]) details.get(0);
			jlMap.put("JobTitle",(HrJobTitle)result[0]);
			jlMap.put("Location",(Location)result[1]);
			}
			else {
				jlMap.put("JobTitle",new HrJobTitle());
				jlMap.put("Location",new Location());
			}
		}
		}
		return jlMap;
	}

    public List<HrPost> getPostsForJobTitleAndLocation(Location location, HrJobTitle hrJobTitle) {
        return sessionFactory.getCurrentSession().createCriteria(HrPost.class).add(Restrictions.eq("location", location)).add(Restrictions.eq("hrJobTitle",hrJobTitle)).list();
    }
    @SuppressWarnings("unchecked")
    public List<HrPost> getOpenPostByJobTitle(Integer locationId){
        List<HrPost> postList=new ArrayList<HrPost>();
        ConceptService cs=Context.getConceptService();
        List<Concept> concepts=cs.getConceptsByMapping("Post status current","HR Module");
        Concept openPost=null;
        if(concepts!=null){
            Iterator<Concept> caliter=concepts.iterator();
            while(caliter.hasNext())
                if((openPost=caliter.next()).getName().getName().equals("Open"))
                    break;
        }
        Criteria crit=sessionFactory.getCurrentSession().createCriteria(HrPost.class).createAlias("location","postLocation").add(Restrictions.eq("postLocation.id",locationId)).add(Restrictions.eq("status",openPost)).add(Restrictions.eq("retired", false)).setProjection(Projections.projectionList().add(Projections.groupProperty("hrJobTitle")).add(Projections.min("postId")));
        List<Object[]> objectList=crit.list();
        Iterator<Object[]> iter=objectList.iterator();
        List<Integer> posts=new ArrayList<Integer>();
        while (iter.hasNext()) {
            posts.add((Integer)iter.next()[1]);
        }
        if(openPost!=null && posts.size()>0)
            postList=sessionFactory.getCurrentSession().createCriteria(HrPost.class).add(Restrictions.in("postId",posts)).list();

        return postList;
    }

    public List<HrPost> getPostsByJobTitle(Integer locationId){
        List<HrPost> postList=new ArrayList<HrPost>();
        Criteria crit=sessionFactory.getCurrentSession().createCriteria(HrPost.class).createAlias("location","postLocation").add(Restrictions.eq("postLocation.id",locationId)).add(Restrictions.eq("retired",false)).setProjection(Projections.projectionList().add(Projections.groupProperty("hrJobTitle")).add(Projections.min("postId")));
        List<Object[]> objectList=crit.list();
        Iterator<Object[]> iter=objectList.iterator();
        List<Integer> posts=new ArrayList<Integer>();
        while (iter.hasNext()) {
            posts.add((Integer)iter.next()[1]);
        }
        if(posts.size()>0)
            postList=sessionFactory.getCurrentSession().createCriteria(HrPost.class).add(Restrictions.in("postId",posts)).list();
        return postList;
    }
}
