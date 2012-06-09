package org.openmrs.module.hr.api.db.hibernate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.openmrs.module.hr.HrLeave;
import org.openmrs.module.hr.api.db.HRLeaveDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHRLeaveDAO implements HRLeaveDAO{

    protected Log log = LogFactory.getLog(this.getClass());

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

}
