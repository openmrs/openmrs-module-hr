package org.openmrs.module.hr.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.openmrs.module.hr.HrStaffAttribute;
import org.openmrs.module.hr.api.db.HRStaffAttributeDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHRStaffAttributeDAO implements HRStaffAttributeDAO {

    protected Log log = LogFactory.getLog(this.getClass());

    private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    public void saveStaffAttribute(HrStaffAttribute staffAttribute) {
        log.debug("saving HrStaffAttribute instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(staffAttribute);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void deleteStaffAttribute(HrStaffAttribute staffAttribute) {
        log.debug("deleting HrStaffAttribute instance");
        try {
            sessionFactory.getCurrentSession().delete(staffAttribute);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public List<HrStaffAttribute> findStaffAttributeByExample(HrStaffAttribute staffAttribute) {
        log.debug("finding staff attribute by example");
        try {
            List<HrStaffAttribute> results = sessionFactory.getCurrentSession().createCriteria(HrStaffAttribute.class).add(Example.create(staffAttribute)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrStaffAttribute>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public HrStaffAttribute getStaffAttributeById( int id) {
        log.debug("getting HrStaffAttribute instance with id: " + id);
        try {
            HrStaffAttribute instance = (HrStaffAttribute) sessionFactory.getCurrentSession().get(HrStaffAttribute.class, id);
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
