package org.openmrs.module.hr.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.api.db.HRJobTitleDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHRJobTitleDAO implements HRJobTitleDAO{

    protected Log log = LogFactory.getLog(this.getClass());

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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
}
