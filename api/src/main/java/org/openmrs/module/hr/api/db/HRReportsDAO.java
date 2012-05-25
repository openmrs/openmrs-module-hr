package org.openmrs.module.hr.api.db;

import org.openmrs.annotation.Authorized;
import org.openmrs.module.hr.HrReport;

import java.util.List;

public interface HRReportsDAO {

    @Authorized("View Reports")
    public List<HrReport> getHrReports();

    @Authorized("View Reports")
    public HrReport getHrReport(Integer reportId);
}
