package org.openmrs.module.hr.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.hr.HrCompetency;
import org.openmrs.module.hr.HrEducation;
import org.openmrs.module.hr.HrEvaluation;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.api.db.HREvaluationDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHREvaluationDAO implements HREvaluationDAO{

    protected Log log = LogFactory.getLog(this.getClass());

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

    public List<HrEvaluation> getEvaluationsForStaff(HrStaff staff) {
        log.debug("getting evaluation for staff and competency");
        try {
            List<HrEvaluation> evaluationList=sessionFactory.getCurrentSession().createCriteria(HrEvaluation.class).add(Restrictions.eq("hrStaff", staff)).list();
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

}
