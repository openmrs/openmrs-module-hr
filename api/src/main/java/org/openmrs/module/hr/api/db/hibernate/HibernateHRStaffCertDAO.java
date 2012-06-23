package org.openmrs.module.hr.api.db.hibernate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffCert;
import org.openmrs.module.hr.api.db.HRStaffCertDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHRStaffCertDAO implements HRStaffCertDAO {

    protected Log log = LogFactory.getLog(this.getClass());

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
            if(results!=null)
                log.debug("find by example successful, result size: " + results.size());
            else
                results=new ArrayList<HrStaffCert>();
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

    public List<HrStaffCert> getAllCertificatesForStaff(HrStaff staff) {
        log.debug("getting HrAssignment instance with given post history");
        try {
            List<HrStaffCert> hrStaffCertList=sessionFactory.getCurrentSession().createCriteria(HrStaffCert.class).add(Restrictions.eq("hrStaff", staff)).list();
            if (hrStaffCertList==null) {
                hrStaffCertList=new ArrayList<HrStaffCert>();
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return hrStaffCertList;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}
