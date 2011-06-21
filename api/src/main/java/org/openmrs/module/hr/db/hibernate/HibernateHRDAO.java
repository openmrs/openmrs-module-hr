package org.openmrs.module.hr.db.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.hr.HrAssignment;
import org.openmrs.module.hr.HrCertificate;
import org.openmrs.module.hr.HrCompetency;
import org.openmrs.module.hr.HrEducation;
import org.openmrs.module.hr.HrEvaluation;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrLeave;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.HrPostHistory;
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
            log.debug("find by example successful, result size: " + results.size());
            return results;
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
                log.debug("get successful, no certificates found");
            }
            else {
                log.debug("get successful, returining certificates found");
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
            log.debug("find by example successful, result size: " + results.size());
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
                 log.debug("get successful, no competencies found");
             }
             else {
                 log.debug("get successful, returining competencies found");
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
            log.debug("find by example successful, result size: " + results.size());
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
            log.debug("get successful");
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
            log.debug("find by example successful, result size: " + results.size());
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
            log.debug("get successful");
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
            log.debug("find by example successful, result size: " + results.size());
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
                log.debug("get successful, no job titles found");
            }
            else {
                log.debug("get successful, returining job titles found");
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
            log.debug("find by example successful, result size: " + results.size());
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
            log.debug("find by example successful, result size: " + results.size());
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
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
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
    public void savePost(HrPost post) {
        log.debug("saving HrPost instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(post);
            log.debug("save successful");
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
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    public List<HrPost> getAllPosts() {
    	log.debug("getting all Posts");
        try {
            List<HrPost> postList=sessionFactory.getCurrentSession().createCriteria(HrPost.class).list();
            if (postList==null) {
                log.debug("get successful, no posts found");
            }
            else {
                log.debug("get successful, returining posts found");
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
            sessionFactory.getCurrentSession().saveOrUpdate(staff);
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
            log.debug("find by example successful, result size: " + results.size());
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
            log.debug("find by example successful, result size: " + results.size());
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
    public void deleteStaffAttribute(HrStaffAttributeType staffAttributeType) {
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
            log.debug("find by example successful, result size: " + results.size());
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
    public List<HrStaffAttributeType> getAllStaffAttributeTypes() {
    	log.debug("getting all Staff attribute types");
        try {
            List<HrStaffAttributeType> staffAttributeTypeList=sessionFactory.getCurrentSession().createCriteria(HrStaffAttributeType.class).list();
            if (staffAttributeTypeList==null) {
                log.debug("get successful, no staff attribute types found");
            }
            else {
                log.debug("get successful, returining saff attribute types found");
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
            log.debug("find by example successful, result size: " + results.size());
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
    public void deleteStaffCert(HrStaffNote staffNote) {
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
            log.debug("find by example successful, result size: " + results.size());
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
                log.debug("get successful, no staff notes found");
            }
            else {
                log.debug("get successful, returining saff notes found");
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
            log.debug("find by example successful, result size: " + results.size());
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
            log.debug("find by example successful, result size: " + results.size());
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
            log.debug("find by example successful, result size: " + results.size());
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
}
