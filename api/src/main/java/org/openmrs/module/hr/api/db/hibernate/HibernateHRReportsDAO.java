package org.openmrs.module.hr.api.db.hibernate;

import org.hibernate.SessionFactory;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hr.HrReport;
import org.openmrs.module.hr.api.db.HRReportsDAO;

import java.util.List;

public class HibernateHRReportsDAO implements HRReportsDAO{

    private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    public HrReport getHrReport(Integer reportId) throws DAOException {
		HrReport hrReport = (HrReport) sessionFactory.getCurrentSession().get(HrReport.class, reportId);
		if(hrReport!=null)
		hrReport.initParamsFromLoad();
		return hrReport;
	}

	public List<HrReport> getHrReports() throws DAOException {
		List<HrReport> reports = sessionFactory.getCurrentSession().createCriteria(HrReport.class).list();
		if(reports!=null){
		for (HrReport hrReport : reports) {
			hrReport.initParamsFromLoad();
		}
		}
		return reports;
	}
}
