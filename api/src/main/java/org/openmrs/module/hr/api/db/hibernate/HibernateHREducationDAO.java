package org.openmrs.module.hr.api.db.hibernate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.hr.HrEducation;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.api.db.HREducationDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHREducationDAO  implements HREducationDAO{

    protected Log log = LogFactory.getLog(this.getClass());

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

}
