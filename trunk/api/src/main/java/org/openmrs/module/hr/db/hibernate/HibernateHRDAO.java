package org.openmrs.module.hr.db.hibernate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.Person;
import org.openmrs.api.APIException;
import org.openmrs.api.ConceptService;
import org.openmrs.api.LocationService;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hr.HrAssignment;
import org.openmrs.module.hr.HrCertificate;
import org.openmrs.module.hr.HrCompetency;
import org.openmrs.module.hr.HrEducation;
import org.openmrs.module.hr.HrEvaluation;
import org.openmrs.module.hr.HrIscoCodes;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrLeave;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.HrPostHistory;
import org.openmrs.module.hr.HrReport;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffAttribute;
import org.openmrs.module.hr.HrStaffAttributeType;
import org.openmrs.module.hr.HrStaffCert;
import org.openmrs.module.hr.HrStaffNote;
import org.openmrs.module.hr.HrTrainPerson;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.HrTrainingClass;
import org.openmrs.module.hr.db.HRDAO;

public class HibernateHRDAO implements HRDAO {

	protected Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
	/**
	 * getter for session factory
	 * 
	 * @return
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * setter for session factory
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void saveAssignment(HrAssignment assignment) {
        log.debug("saving HrAssignment instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(assignment);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
  
    public void deleteAssignment(HrAssignment assignment) {
        log.debug("deleting HrAssignment instance");
        try {
            sessionFactory.getCurrentSession().delete(assignment);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public List<HrAssignment> getAssignmentsForPostHistory(HrPostHistory postHistory) {
        log.debug("getting HrAssignment instance with given post history");
        try {
        	List<HrAssignment> assignmentList=sessionFactory.getCurrentSession().createCriteria(HrAssignment.class).add(Restrictions.eq("hrPostHistory", postHistory)).list();
            if (assignmentList==null) {
            	assignmentList=new ArrayList<HrAssignment>();
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return assignmentList;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    public List<HrAssignment> findAssignmentByExample(HrAssignment assignment) {
        log.debug("finding HrAssignment instance by example");
        try {
            List<HrAssignment> results =sessionFactory.getCurrentSession().createCriteria(HrAssignment.class).add(Example.create(assignment)).list();
            if(results!=null){
            log.debug("find by example successful, result size: " + results.size());
            return results;
            }
            return new ArrayList<HrAssignment>();
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
    public HrAssignment getAssignmentById( int id) {
        log.debug("getting HrAssignment instance with id: " + id);
        try {
            HrAssignment instance = (HrAssignment) sessionFactory.getCurrentSession().get(HrAssignment.class, id);
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
    
    public void saveCertificate(HrCertificate certificate) {
        log.debug("saving HrCertificate instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(certificate);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deleteCertificate(HrCertificate certificate) {
        log.debug("deleting HrCertificate instance");
        try {
            sessionFactory.getCurrentSession().delete(certificate);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrCertificate> getAllCertificates() {
    	log.debug("getting all HrCertificates");
        try {
            List<HrCertificate> certificateList = sessionFactory.getCurrentSession().createCriteria(HrCertificate.class).list();
            if (certificateList==null) {
            	certificateList=new ArrayList<HrCertificate>();
                log.debug("get successful, no certificates found");
            }
            else {
                log.debug("get successful, returning certificates found");
            }
            return certificateList;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
		
	}
    public List<HrCertificate> findCertificatesByExample(HrCertificate certificate) {
        log.debug("finding Certificates by example");
        try {
            List<HrCertificate> results = sessionFactory.getCurrentSession().createCriteria(HrCertificate.class).add(Example.create(certificate)).list();
            if(results!=null){
            log.debug("find by example successful, result size: " + results.size());
            }
            else {
				results=new ArrayList<HrCertificate>();
			}
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
    public HrCertificate getCertificateById( int id) {
        log.debug("getting HrCertificate instance with id: " + id);
        try {
            HrCertificate instance = (HrCertificate) sessionFactory.getCurrentSession().get(HrCertificate.class, id);
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
    public void saveCompetency(HrCompetency competency) {
        log.debug("saving HrCompetency instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(competency);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deleteCompetency(HrCompetency competency) {
        log.debug("deleting HrCompetency instance");
        try {
            sessionFactory.getCurrentSession().delete(competency);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrCompetency> getAllCompetencies() {
    	 log.debug("getting all competencies");
         try {
             List<HrCompetency> competencyList=sessionFactory.getCurrentSession().createCriteria(HrCompetency.class).list();
             if (competencyList==null) {
            	 competencyList=new ArrayList<HrCompetency>();
                 log.debug("get successful, no competencies found");
             }
             else {
                 log.debug("get successful, returning competencies found");
             }
             return competencyList;
         }
         
         catch (RuntimeException re) {
             log.error("get failed", re);
             throw re;
         }
        
    }
    public List<HrCompetency> findCompetencyByExample(HrCompetency competency) {
        log.debug("finding competencies by example");
        try {
            List<HrCompetency> results = sessionFactory.getCurrentSession().createCriteria(HrCompetency.class).add(Example.create(competency)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrCompetency>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
    public HrCompetency getCompetencyById( int id) {
        log.debug("getting HrCompetency instance with id: " + id);
        try {
            HrCompetency instance = (HrCompetency) sessionFactory.getCurrentSession().get(HrCompetency.class, id);
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
    public void saveEducation(HrEducation education) {
        log.debug("saving HrEducation instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(education);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deleteEducation(HrEducation education) {
        log.debug("deleting HrEducation instance");
        try {
            sessionFactory.getCurrentSession().delete(education);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrEducation> getEducationForStaff(HrStaff staff) {
    	log.debug("getting Education for staff");
        try {
            List<HrEducation> educationList=sessionFactory.getCurrentSession().createCriteria(HrEducation.class).add(Restrictions.eq("hrStaff", staff)).list();
            if(educationList!=null)
            log.debug("get successful");
            else 
			educationList=new ArrayList<HrEducation>();
            return educationList;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
	}
    public List<HrEducation> findEducationByExample(HrEducation education) {
        log.debug("finding educations by example");
        try {
            List<HrEducation> results = sessionFactory.getCurrentSession().createCriteria(HrEducation.class).add(Example.create(education)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrEducation>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
    public HrEducation getEducationById( int id) {
        log.debug("getting HrEducation instance with id: " + id);
        try {
            HrEducation instance = (HrEducation) sessionFactory.getCurrentSession().get(HrEducation.class, id);
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
    public void saveEvaluation(HrEvaluation evaluation) {
        log.debug("saving HrEvaluation instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(evaluation);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deleteEvaluation(HrEvaluation evaluation) {
        log.debug("deleting HrEvaluation instance");
        try {
            sessionFactory.getCurrentSession().delete(evaluation);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrEvaluation> getEvaluationsForStaffAndCompetency(HrStaff staff,HrCompetency competency){
    	log.debug("getting evaluation for staff and competency");
        try {
            List<HrEvaluation> evaluationList=sessionFactory.getCurrentSession().createCriteria(HrEducation.class).add(Restrictions.eq("hrStaff", staff)).add(Restrictions.eq("hrCompetency", competency)).list();
            if(evaluationList!=null)
            log.debug("get successful");
            else
            	evaluationList=new ArrayList<HrEvaluation>();
            return evaluationList;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }	
	}
    public List<HrEvaluation> findEvaluationByExample(HrEvaluation evaluation) {
        log.debug("finding evaluations by example");
        try {
            List<HrEvaluation> results = sessionFactory.getCurrentSession().createCriteria(HrEvaluation.class).add(Example.create(evaluation)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrEvaluation>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 

    public HrEvaluation getEvaluationById( int id) {
        log.debug("getting HrEvaluation instance with id: " + id);
        try {
            HrEvaluation instance = (HrEvaluation) sessionFactory.getCurrentSession().get(HrEvaluation.class, id);
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
    public HrIscoCodes getIscoCodeById( String id) {
        log.debug("getting HrIscoCode instance with id: " + id);
        try {
            HrIscoCodes instance = (HrIscoCodes) sessionFactory.getCurrentSession().get(HrIscoCodes.class, id);
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
    	public List<HrIscoCodes> getAllIscoCodes() {
    		log.debug("getting all Isco Codes");
    		try {
            List<HrIscoCodes> iscoCodeList=sessionFactory.getCurrentSession().createCriteria(HrIscoCodes.class).list();
            if (iscoCodeList==null) {
            	iscoCodeList=new ArrayList<HrIscoCodes>();
                log.debug("get successful, no isco codes found");
            }
            else {
                log.debug("get successful, returning isco codes found");
            }
            return iscoCodeList;
        }
        
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
		
	}
    public void saveJobTitle(HrJobTitle jobTitle) {
        log.debug("saving HrJobTitle instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(jobTitle);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deleteJobTitle(HrJobTitle jobTitle) {
        log.debug("deleting HrJobTitle instance");
        try {
            sessionFactory.getCurrentSession().delete(jobTitle);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrJobTitle> getAllJobTitles() {
    	log.debug("getting all job titles");
        try {
            List<HrJobTitle> jobTitleList=sessionFactory.getCurrentSession().createCriteria(HrJobTitle.class).list();
            if (jobTitleList==null) {
            	jobTitleList=new ArrayList<HrJobTitle>();
                log.debug("get successful, no job titles found");
            }
            else {
                log.debug("get successful, returning job titles found");
            }
            return jobTitleList;
        }
        
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    public List<HrJobTitle> findJobTitleByExample(HrJobTitle jobTitle) {
        log.debug("finding Job Titles by example");
        try {
            List<HrJobTitle> results = sessionFactory.getCurrentSession().createCriteria(HrJobTitle.class).add(Example.create(jobTitle)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            results=new ArrayList<HrJobTitle>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
    public HrJobTitle getJobTitleById( int id) {
        log.debug("getting HrJobTitle instance with id: " + id);
        try {
            HrJobTitle instance = (HrJobTitle) sessionFactory.getCurrentSession().get(HrJobTitle.class, id);
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
    
    public void saveLeave(HrLeave leave) {
        log.debug("saving HrLeave instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(leave);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deleteLeave(HrLeave leave) {
        log.debug("deleting HrLeave instance");
        try {
            sessionFactory.getCurrentSession().delete(leave);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrLeave> findLeaveByExample(HrLeave leave) {
        log.debug("finding leaves by example");
        try {
            List<HrLeave> results = sessionFactory.getCurrentSession().createCriteria(HrLeave.class).add(Example.create(leave)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrLeave>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
    public HrLeave getLeaveById( int id) {
        log.debug("getting HrLeave instance with id: " + id);
        try {
            HrLeave instance = (HrLeave) sessionFactory.getCurrentSession().get(HrLeave.class, id);
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
    public void savePostHistory(HrPostHistory postHistory) {
        log.debug("saving HrPostHistory instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(postHistory);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deletePostHistory(HrPostHistory postHistory) {
        log.debug("deleting HrPostHistory instance");
        try {
            sessionFactory.getCurrentSession().delete(postHistory);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrPostHistory> findPostHistoryByExample(HrPostHistory postHistory) {
        log.debug("finding post histories by example");
        try {
            List<HrPostHistory> results = sessionFactory.getCurrentSession().createCriteria(HrPostHistory.class).add(Example.create(postHistory)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrPostHistory>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
	public List<HrPostHistory> getPostHistoriesForStaff(HrStaff staff) {
        log.debug("finding post histories by staff");
        try {
            List<HrPostHistory> results = sessionFactory.getCurrentSession().createCriteria(HrPostHistory.class).add(Restrictions.eq("hrStaff",staff)).addOrder(Order.desc("startDate")).list();
            if(results!=null)
            log.debug("get successful, result size: " + results.size());
            else
            	results=new ArrayList<HrPostHistory>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    } 
    public HrPostHistory getPostHistoryById( int id) {
        log.debug("getting HrPostHistory instance with id: " + id);
        try {
            HrPostHistory instance = (HrPostHistory) sessionFactory.getCurrentSession().get(HrPostHistory.class, id);
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
    	LocationService locService=Context.getLocationService();
    	ConceptService conceptService=Context.getConceptService();
    	List<Location> hrManagedLocations=locService.getLocationsByTag(locService.getLocationTagByName("HR Managed"));
    	List<Concept> postStatusCurrent=conceptService.getConceptsByMapping("Post status current", "HR Module");
        try {
        	if(!includeAllPosts)
        		criteria.add(Restrictions.in("status",postStatusCurrent));
           	if(!includeAllLocations){
           		criteria.add(Restrictions.in("location",hrManagedLocations));
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
    public List<HrStaff> getAllStaff(boolean includeAllStaff,boolean includeAllLocations) {
    	log.debug("getting all Staff");
    	List<HrStaff> staffList=null;
    	Criteria criteria=sessionFactory.getCurrentSession().createCriteria(HrStaff.class);
    	LocationService locService=Context.getLocationService();
    	ConceptService conceptService=Context.getConceptService();
    	List<Location> hrManagedLocations=locService.getLocationsByTag(locService.getLocationTagByName("HR Managed"));
    	List<Concept> staffStatusCurrent=conceptService.getConceptsByMapping("Staff status current", "HR Module");
        try {
           	if(!includeAllStaff)
        		criteria.add(Restrictions.in("staffStatus",staffStatusCurrent));
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
    public void saveStaffAttribute(HrStaffAttribute staffAttribute) {
        log.debug("saving HrStaffAttribute instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(staffAttribute);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deleteStaffAttribute(HrStaffAttribute staffAttribute) {
        log.debug("deleting HrStaffAttribute instance");
        try {
            sessionFactory.getCurrentSession().delete(staffAttribute);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrStaffAttribute> findStaffAttributeByExample(HrStaffAttribute staffAttribute) {
        log.debug("finding staff attribute by example");
        try {
            List<HrStaffAttribute> results = sessionFactory.getCurrentSession().createCriteria(HrStaffAttribute.class).add(Example.create(staffAttribute)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrStaffAttribute>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
    public HrStaffAttribute getStaffAtrributeById( int id) {
        log.debug("getting HrStaffAttribute instance with id: " + id);
        try {
            HrStaffAttribute instance = (HrStaffAttribute) sessionFactory.getCurrentSession().get(HrStaffAttribute.class, id);
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
    public void saveStaffAttributeType(HrStaffAttributeType staffAttributeType) {
        log.debug("saving HrStaffAttributeType instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(staffAttributeType);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deleteStaffAttributeType(HrStaffAttributeType staffAttributeType) {
        log.debug("deleting HrStaffAttributeType instance");
        try {
            sessionFactory.getCurrentSession().delete(staffAttributeType);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrStaffAttributeType> findStaffAttributeTypeByExample(HrStaffAttributeType staffAttributeType) {
        log.debug("finding staffAttributeType by example");
        try {
            List<HrStaffAttributeType> results = sessionFactory.getCurrentSession().createCriteria(HrStaffAttributeType.class).add(Example.create(staffAttributeType)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrStaffAttributeType>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
    public HrStaffAttributeType getStaffAttributeTypeById( int id) {
        log.debug("getting HrStaffAttributeType instance with id: " + id);
        try {
            HrStaffAttributeType instance = (HrStaffAttributeType) sessionFactory.getCurrentSession().get(HrStaffAttributeType.class, id);
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
    public HrStaffAttributeType getStaffAttributeTypeByName(String name) {
        log.debug("getting HrStaffAttributeType instance with nane: " + name);
        try {
            List<HrStaffAttributeType> results=sessionFactory.getCurrentSession().createCriteria(HrStaffAttributeType.class).add(Restrictions.eq("name",name)).list();
            HrStaffAttributeType instance=null;
            if(results.size()>1){
            	throw new APIException("Multiple attribute types with same name found");	
            }
            else if (results.size()==1) {
            	instance=results.get(0);
			}
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
    public List<HrStaffAttributeType> getAllStaffAttributeTypes() {
    	log.debug("getting all Staff attribute types");
        try {
            List<HrStaffAttributeType> staffAttributeTypeList=sessionFactory.getCurrentSession().createCriteria(HrStaffAttributeType.class).list();
            if (staffAttributeTypeList==null) {
            	staffAttributeTypeList=new ArrayList<HrStaffAttributeType>();
                log.debug("get successful, no staff attribute types found");
            }
            else {
                log.debug("get successful, returning staff attribute types found");
            }
            return staffAttributeTypeList;
        }
        
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
		
	}
    public void saveStaffCert(HrStaffCert staffCert) {
        log.debug("saving HrStaffCert instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(staffCert);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deleteStaffCert(HrStaffCert staffCert) {
        log.debug("deleting HrStaffCert instance");
        try {
            sessionFactory.getCurrentSession().delete(staffCert);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrStaffCert> findStaffCertByExample(HrStaffCert staffCert) {
        log.debug("finding Staff Certificates by example");
        try {
            List<HrStaffCert> results = sessionFactory.getCurrentSession().createCriteria(HrStaffCert.class).add(Example.create(staffCert)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrStaffCert>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
    public HrStaffCert getStaffCertById( int id) {
        log.debug("getting HrStaffCert instance with id: " + id);
        try {
            HrStaffCert instance = (HrStaffCert) sessionFactory.getCurrentSession().get(HrStaffCert.class, id);
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
    public void saveStaffNote(HrStaffNote staffNote) {
        log.debug("saving HrStaffNote instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(staffNote);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deleteStaffNote(HrStaffNote staffNote) {
        log.debug("deleting staffNote instance");
        try {
            sessionFactory.getCurrentSession().delete(staffNote);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrStaffNote> findStaffNoteByExample(HrStaffNote staffCert) {
        log.debug("finding Staff Notes by example");
        try {
            List<HrStaffNote> results = sessionFactory.getCurrentSession().createCriteria(HrStaffNote.class).add(Example.create(staffCert)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrStaffNote>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    public List<HrStaffNote> getAllStaffNotes() {
    	log.debug("getting all Staff Notes");
        try {
            List<HrStaffNote> staffNoteList=sessionFactory.getCurrentSession().createCriteria(HrStaffNote.class).list();
            if (staffNoteList==null) {
            	staffNoteList=new ArrayList<HrStaffNote>();
                log.debug("get successful, no staff notes found");
            }
            else {
                log.debug("get successful, returning staff notes found");
            }
            return staffNoteList;
        }
        
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
		
	}
    public HrStaffNote getStaffNoteById(int id) {
    	log.debug("getting HrStaffNote instance with id: " + id);
        try {
            HrStaffNote staffNote = (HrStaffNote) sessionFactory.getCurrentSession().get(HrStaffNote.class, id);
            if (staffNote==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return staffNote;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    public void saveTraining(HrTraining training) {
        log.debug("saving HrTraining instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(training);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deleteTraining(HrTraining training) {
        log.debug("deleting HrTraining instance");
        try {
            sessionFactory.getCurrentSession().delete(training);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrTraining> findTrainingByExample(HrTraining training) {
        log.debug("finding Training by example");
        try {
            List<HrTraining> results = sessionFactory.getCurrentSession().createCriteria(HrTraining.class).add(Example.create(training)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrTraining>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
    public HrTraining getTrainingById( int id) {
        log.debug("getting HrTraining instance with id: " + id);
        try {
            HrTraining instance = (HrTraining) sessionFactory.getCurrentSession().get(HrTraining.class, id);
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
    public void saveTrainingClass(HrTrainingClass trainingClass) {
        log.debug("saving trainingClass instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(trainingClass);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deleteTrainingClass(HrTrainingClass trainingClass) {
        log.debug("deleting trainingClass instance");
        try {
            sessionFactory.getCurrentSession().delete(trainingClass);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrTrainingClass> findTrainingClassByExample(HrTrainingClass trainingClass) {
        log.debug("finding training class by example");
        try {
            List<HrTrainingClass> results = sessionFactory.getCurrentSession().createCriteria(HrTrainingClass.class).add(Example.create(trainingClass)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrTrainingClass>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
    public HrTrainingClass getTrainingClassById( int id) {
        log.debug("getting HrTrainingClass instance with id: " + id);
        try {
            HrTrainingClass instance = (HrTrainingClass) sessionFactory.getCurrentSession().get(HrTrainingClass.class, id);
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
    public void saveTrainPerson(HrTrainPerson trainPerson) {
        log.debug("saving train person instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(trainPerson);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deleteTrainPerson(HrTrainPerson trainPerson) {
        log.debug("deleting train person instance");
        try {
            sessionFactory.getCurrentSession().delete(trainPerson);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrTrainPerson> findTrainPersonByExample(HrTrainPerson trainPerson) {
        log.debug("finding Train Person by example");
        try {
            List<HrTrainPerson> results = sessionFactory.getCurrentSession().createCriteria(HrTrainPerson.class).add(Example.create(trainPerson)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrTrainPerson>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
    public HrTrainPerson getTrainPersonById( int id) {
        log.debug("getting HrTrainPerson instance with id: " + id);
        try {
            HrTrainPerson instance = (HrTrainPerson) sessionFactory.getCurrentSession().get(HrTrainPerson.class, id);
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
	public HrPostHistory getCurrentPostForStaff(int staffId){
		List<HrPostHistory> postHistoryList=sessionFactory.getCurrentSession().createCriteria(HrPostHistory.class).createAlias("hrStaff","staff").add(Restrictions.eq("staff.staffId", staffId)).add(Restrictions.isNull("endDate")).list();
		if(postHistoryList==null)
			return null;
		if(postHistoryList.size()>1)
			throw new APIException("Multiple current posts found");
		if(postHistoryList.size()==1)
		return postHistoryList.get(0);
		else {
			return null;
		}
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
	public HrReport getHrReport(Integer reportId) throws DAOException {
		HrReport hrReport = (HrReport) sessionFactory.getCurrentSession().get(HrReport.class, reportId);
		if(hrReport!=null)
		hrReport.initParamsFromLoad();
		return hrReport;
	}

	public List<HrReport> getHrReports() throws DAOException {
		List<HrReport> reports = sessionFactory.getCurrentSession().createCriteria(HrReport.class).list();
		if(reports!=null){
		for (HrReport hrReport : reports) {
			hrReport.initParamsFromLoad();
		}
		}
		return reports;
	}
	public HrPost wasPostOpen(HrPost post,Date start,Date end){
		List<HrPost> criteriaPosts=null;
		if(post!=null && start!=null && end!=null){
		criteriaPosts=sessionFactory.getCurrentSession().createCriteria(HrPost.class).add(Restrictions.eq("location",post.getLocation())).add(Restrictions.eq("hrJobTitle",post.getHrJobTitle())).list();
		List<HrPostHistory> postHistories=sessionFactory.getCurrentSession().createCriteria(HrPostHistory.class).add(Restrictions.in("hrPost",criteriaPosts)).list();
		List<HrPost> occupiedPosts=new ArrayList<HrPost>();
		for(HrPostHistory postHistory:postHistories)
		{
			if(postHistory.getStartDate()!=null)
			{
				if(postHistory.getEndDate()==null){
					if(!((start.before(postHistory.getStartDate())||start.compareTo(postHistory.getStartDate())==0 )&& (end.before(postHistory.getEndDate()) || end.compareTo(postHistory.getStartDate())==0))){
						HrPost thispost=getPostById(postHistory.getHrPost().getId());	
						if(!occupiedPosts.contains(thispost))
								occupiedPosts.add(thispost);
					}
				}
				else{
					if((end.after(postHistory.getStartDate()))&&(start.before(postHistory.getEndDate()))){
						HrPost thispost=getPostById(postHistory.getHrPost().getId());	
						if(!occupiedPosts.contains(thispost))
								occupiedPosts.add(thispost);
					}
				}
					
			}
		}
		criteriaPosts.removeAll(occupiedPosts);
		}
		if(criteriaPosts!=null && criteriaPosts.size()>=1)
		return criteriaPosts.get(0);
		else
			return null;
	}
	
}
