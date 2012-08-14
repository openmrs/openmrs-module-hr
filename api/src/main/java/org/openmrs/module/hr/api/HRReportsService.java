package org.openmrs.module.hr.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hr.HrReport;
import org.openmrs.module.hr.PrivilegeConstants;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRReportsService {

    @Authorized(PrivilegeConstants.RUN_REPORTS)
    public List<HrReport> getHrReports() throws DAOException;

    @Authorized(PrivilegeConstants.RUN_REPORTS)
	public HrReport getHrReport(Integer reportId) throws DAOException;
}
