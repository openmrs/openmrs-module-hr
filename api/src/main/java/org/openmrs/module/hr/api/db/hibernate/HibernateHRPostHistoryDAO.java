package org.openmrs.module.hr.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.APIException;
import org.openmrs.module.hr.HrPostHistory;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.api.db.HRPostHistoryDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHRPostHistoryDAO implements HRPostHistoryDAO{


    protected Log log = LogFactory.getLog(this.getClass());

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    public void savePostHistory(HrPostHistory postHistory) {
        log.debug("saving HrPostHistory instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(postHistory);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deletePostHistory(HrPostHistory postHistory) {
        log.debug("deleting HrPostHistory instance");
        try {
            sessionFactory.getCurrentSession().delete(postHistory);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrPostHistory> findPostHistoryByExample(HrPostHistory postHistory) {
        log.debug("finding post histories by example");
        try {
            List<HrPostHistory> results = sessionFactory.getCurrentSession().createCriteria(HrPostHistory.class).add(Example.create(postHistory)).list();
            if(results!=null)
            log.debug("find by example successful, result size: " + results.size());
            else
            	results=new ArrayList<HrPostHistory>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
	public List<HrPostHistory> getPostHistoriesForStaff(HrStaff staff) {
        log.debug("finding post histories by staff");
        try {
            List<HrPostHistory> results = sessionFactory.getCurrentSession().createCriteria(HrPostHistory.class).add(Restrictions.eq("hrStaff", staff)).addOrder(Order.desc("startDate")).list();
            if(results!=null)
            log.debug("get successful, result size: " + results.size());
            else
            	results=new ArrayList<HrPostHistory>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

   public HrPostHistory getCurrentPostForStaff(int staffId){
		List<HrPostHistory> postHistoryList=sessionFactory.getCurrentSession().createCriteria(HrPostHistory.class).createAlias("hrStaff","staff").add(Restrictions.eq("staff.staffId", staffId)).add(Restrictions.isNull("endDate")).list();
		if(postHistoryList==null)
			return null;
		if(postHistoryList.size()>1)
			throw new APIException("Multiple current posts found");
		if(postHistoryList.size()==1)
		return postHistoryList.get(0);
		else {
			return null;
		}
	}


    public HrPostHistory getPostHistoryById( int id) {
        log.debug("getting HrPostHistory instance with id: " + id);
        try {
            HrPostHistory instance = (HrPostHistory) sessionFactory.getCurrentSession().get(HrPostHistory.class, id);
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
