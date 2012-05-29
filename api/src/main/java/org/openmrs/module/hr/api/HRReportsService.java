package org.openmrs.module.hr.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hr.HrReport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRReportsService {

    @Authorized("View Reports")
    public List<HrReport> getHrReports() throws DAOException;

    @Authorized("View Reports")
	public HrReport getHrReport(Integer reportId) throws DAOException;
}
