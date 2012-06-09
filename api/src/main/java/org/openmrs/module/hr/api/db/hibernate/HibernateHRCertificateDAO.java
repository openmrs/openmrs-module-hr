package org.openmrs.module.hr.api.db.hibernate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.openmrs.module.hr.HrCertificate;
import org.openmrs.module.hr.api.db.HRCertificateDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHRCertificateDAO implements HRCertificateDAO{

    protected Log log = LogFactory.getLog(this.getClass());

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

}
