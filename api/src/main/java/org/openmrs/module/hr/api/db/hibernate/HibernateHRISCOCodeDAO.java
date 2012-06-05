package org.openmrs.module.hr.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.hr.HrIscoCodes;
import org.openmrs.module.hr.api.db.HRISCOCodeDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHRISCOCodeDAO implements HRISCOCodeDAO{

    protected Log log = LogFactory.getLog(this.getClass());

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    public HrIscoCodes getIscoCodeById( String id) {
        log.debug("getting HrIscoCode instance with id: " + id);
        try {
            HrIscoCodes instance = (HrIscoCodes) sessionFactory.getCurrentSession().get(HrIscoCodes.class, id);
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
    	public List<HrIscoCodes> getAllIscoCodes() {
    		log.debug("getting all Isco Codes");
    		try {
            List<HrIscoCodes> iscoCodeList=sessionFactory.getCurrentSession().createCriteria(HrIscoCodes.class).list();
            if (iscoCodeList==null) {
            	iscoCodeList=new ArrayList<HrIscoCodes>();
                log.debug("get successful, no isco codes found");
            }
            else {
                log.debug("get successful, returning isco codes found");
            }
            return iscoCodeList;
        }

        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }

	}
}
