package org.openmrs.module.hr.api.db.hibernate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.hr.HrTrainingClass;
import org.openmrs.module.hr.api.db.HRTrainingClassDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHRTrainingClassDAO implements HRTrainingClassDAO {

    protected Log log = LogFactory.getLog(this.getClass());

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

    @Override
    public List<HrTrainingClass> getTariningClasses() {
        log.debug("finding training class by example");
        try {
            List<HrTrainingClass> results = sessionFactory.getCurrentSession().createCriteria(HrTrainingClass.class).list();
            if(results!=null)
                log.debug("get training class, result size: " + results.size());
            else
                results=new ArrayList<HrTrainingClass>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    @Override
    public HrTrainingClass getTrainingClassByUniqueId(String uuid) {
        return (HrTrainingClass) sessionFactory.getCurrentSession().createQuery("from HrTrainingClass where uuid = :uuid")
                .setString("uuid", uuid).uniqueResult();
    }


}
