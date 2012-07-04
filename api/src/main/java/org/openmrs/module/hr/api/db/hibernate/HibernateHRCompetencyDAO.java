package org.openmrs.module.hr.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.openmrs.module.hr.HrCompetency;
import org.openmrs.module.hr.api.db.HRCompetencyDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHRCompetencyDAO implements HRCompetencyDAO{
    protected Log log = LogFactory.getLog(this.getClass());

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

}
