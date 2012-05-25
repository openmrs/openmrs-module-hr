package org.openmrs.module.hr.api.impl;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.hr.HrReport;
import org.openmrs.module.hr.api.HRReportsService;
import org.openmrs.module.hr.api.db.HRReportsDAO;

import java.util.List;

public class HRReportsServiceImpl implements HRReportsService {

    private HRReportsDAO dao;

	public void setDao(HRReportsDAO dao)
	{
		this.dao = dao;
	}

    public List<HrReport> getHrReports() throws DAOException {
		return dao.getHrReports();
	}
	public HrReport getHrReport(Integer reportId) throws DAOException{
		return dao.getHrReport(reportId);
	}
}
