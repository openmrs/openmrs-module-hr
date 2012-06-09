package org.openmrs.module.hr.api.db.hibernate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.openmrs.module.hr.HrStaffNote;
import org.openmrs.module.hr.api.db.HRStaffNoteDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateHRStaffNoteDAO implements HRStaffNoteDAO {

    protected Log log = LogFactory.getLog(this.getClass());

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void saveStaffNote(HrStaffNote staffNote) {
        log.debug("saving HrStaffNote instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(staffNote);
            log.debug("save successful");
        }
        catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public void deleteStaffNote(HrStaffNote staffNote) {
        log.debug("deleting staffNote instance");
        try {
            sessionFactory.getCurrentSession().delete(staffNote);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    public List<HrStaffNote> findStaffNoteByExample(HrStaffNote staffCert) {
        log.debug("finding Staff Notes by example");
        try {
            List<HrStaffNote> results = sessionFactory.getCurrentSession().createCriteria(HrStaffNote.class).add(Example.create(staffCert)).list();
            if(results!=null)
                log.debug("find by example successful, result size: " + results.size());
            else
                results=new ArrayList<HrStaffNote>();
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    public List<HrStaffNote> getAllStaffNotes() {
        log.debug("getting all Staff Notes");
        try {
            List<HrStaffNote> staffNoteList=sessionFactory.getCurrentSession().createCriteria(HrStaffNote.class).list();
            if (staffNoteList==null) {
                staffNoteList=new ArrayList<HrStaffNote>();
                log.debug("get successful, no staff notes found");
            }
            else {
                log.debug("get successful, returning staff notes found");
            }
            return staffNoteList;
        }

        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }

    }
    public HrStaffNote getStaffNoteById(int id) {
        log.debug("getting HrStaffNote instance with id: " + id);
        try {
            HrStaffNote staffNote = (HrStaffNote) sessionFactory.getCurrentSession().get(HrStaffNote.class, id);
            if (staffNote==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return staffNote;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

}
