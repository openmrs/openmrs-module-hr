package org.openmrs.module.hr.api.db.hibernate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.hr.HrAssignment;
import org.openmrs.module.hr.HrPostHistory;
import org.openmrs.module.hr.api.db.HRAssignmentDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHRAssignmentDAO implements HRAssignmentDAO{
    protected Log log = LogFactory.getLog(this.getClass());

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

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


}
