package org.openmrs.module.hr.api;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.hr.HrReport;

import java.util.List;

public interface HRReportsService {
    public List<HrReport> getHrReports() throws DAOException;

	public HrReport getHrReport(Integer reportId) throws DAOException;
}
