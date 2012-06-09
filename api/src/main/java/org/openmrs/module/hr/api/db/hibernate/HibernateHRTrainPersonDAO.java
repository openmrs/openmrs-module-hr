package org.openmrs.module.hr.api.db.hibernate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.openmrs.module.hr.HrTrainPerson;
import org.openmrs.module.hr.api.db.HRTrainPersonDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHRTrainPersonDAO implements HRTrainPersonDAO{

    protected Log log = LogFactory.getLog(this.getClass());

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

}
