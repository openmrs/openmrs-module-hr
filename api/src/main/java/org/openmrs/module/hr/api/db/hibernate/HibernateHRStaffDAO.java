package org.openmrs.module.hr.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.api.ConceptService;
import org.openmrs.api.LocationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.api.db.HRStaffDAO;

import java.sql.Timestamp;
import java.util.*;

public class HibernateHRStaffDAO implements HRStaffDAO {
    protected Log log = LogFactory.getLog(this.getClass());
    private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


    public HrStaff getStaffById( int id) {
        log.debug("getting HrStaff instance with id: " + id);
        try {
            HrStaff instance = (HrStaff) sessionFactory.getCurrentSession().get(HrStaff.class, id);
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

    public void saveStaff(HrStaff staff) {
        log.debug("saving HRStaff instance");
        try {
            if(getStaffById(staff.getId())!=null){
                sessionFactory.getCurrentSession().merge(staff);
            }
            else{
                sessionFactory.getCurrentSession().saveOrUpdate(staff);
            }
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void deleteStaff(HrStaff staff) {
        log.debug("deleting HrStaff instance");
        try {
            sessionFactory.getCurrentSession().delete(staff);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrStaff> findStaffByExample(HrStaff staff) {
        log.debug("finding staff by example");
        try {
            List<HrStaff> results = sessionFactory.getCurrentSession().createCriteria(HrStaff.class).add(Example.create(staff)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrStaff>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    public List<HrStaff> getAllStaff(boolean includeAllStaff,boolean includeAllLocations) {
    	log.debug("getting all Staff");
    	List<HrStaff> staffList=null;
    	Criteria criteria=sessionFactory.getCurrentSession().createCriteria(HrStaff.class);
    	LocationService locService= Context.getLocationService();
    	ConceptService conceptService=Context.getConceptService();
    	List<Location> hrManagedLocations=locService.getLocationsByTag(locService.getLocationTagByName("HR Managed"));
    	List<Concept> staffStatusCurrent=conceptService.getConceptsByMapping("Staff status current", "HR Module");
        try {
           	if(!includeAllStaff)
        		criteria.add(Restrictions.in("staffStatus", staffStatusCurrent));
           	staffList=criteria.list();
           	if(!includeAllLocations){
           		List<HrStaff> removableStaff=new ArrayList<HrStaff>();
        		for(HrStaff staff:staffList){
        			Location loc=(Location)(getCurrentJobLocationForStaff(staff.getId())).get("Location");
        			if(!hrManagedLocations.contains(loc))
        				removableStaff.add(staff);
        		}
        		for(HrStaff staff:removableStaff){
        				staffList.remove(staff);
        		}

        	}

            if (staffList==null) {
            	staffList=new ArrayList<HrStaff>();
                log.debug("get successful, no staff found");
            }
            else {
                log.debug("get successful, returning staff found");
            }
            return staffList;
        }

        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }

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
}
