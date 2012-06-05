package org.openmrs.module.hr.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.APIException;
import org.openmrs.module.hr.HrStaffAttributeType;
import org.openmrs.module.hr.api.db.HRStaffAttributeTypeDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHRStaffAttributeTypeDAO implements HRStaffAttributeTypeDAO{

    protected Log log = LogFactory.getLog(this.getClass());

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    public void saveStaffAttributeType(HrStaffAttributeType staffAttributeType) {
        log.debug("saving HrStaffAttributeType instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(staffAttributeType);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void deleteStaffAttributeType(HrStaffAttributeType staffAttributeType) {
        log.debug("deleting HrStaffAttributeType instance");
        try {
            sessionFactory.getCurrentSession().delete(staffAttributeType);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public List<HrStaffAttributeType> findStaffAttributeTypeByExample(HrStaffAttributeType staffAttributeType) {
        log.debug("finding staffAttributeType by example");
        try {
            List<HrStaffAttributeType> results = sessionFactory.getCurrentSession().createCriteria(HrStaffAttributeType.class).add(Example.create(staffAttributeType)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrStaffAttributeType>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public HrStaffAttributeType getStaffAttributeTypeById( int id) {
        log.debug("getting HrStaffAttributeType instance with id: " + id);
        try {
            HrStaffAttributeType instance = (HrStaffAttributeType) sessionFactory.getCurrentSession().get(HrStaffAttributeType.class, id);
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

    public HrStaffAttributeType getStaffAttributeTypeByName(String name) {
        log.debug("getting HrStaffAttributeType instance with nane: " + name);
        try {
            List<HrStaffAttributeType> results=sessionFactory.getCurrentSession().createCriteria(HrStaffAttributeType.class).add(Restrictions.eq("name", name)).list();
            HrStaffAttributeType instance=null;
            if(results.size()>1){
            	throw new APIException("Multiple attribute types with same name found");
            }
            else if (results.size()==1) {
            	instance=results.get(0);
			}
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

    public List<HrStaffAttributeType> getAllStaffAttributeTypes() {
    	log.debug("getting all Staff attribute types");
        try {
            List<HrStaffAttributeType> staffAttributeTypeList=sessionFactory.getCurrentSession().createCriteria(HrStaffAttributeType.class).list();
            if (staffAttributeTypeList==null) {
            	staffAttributeTypeList=new ArrayList<HrStaffAttributeType>();
                log.debug("get successful, no staff attribute types found");
            }
            else {
                log.debug("get successful, returning staff attribute types found");
            }
            return staffAttributeTypeList;
        }

        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }

	}

}
