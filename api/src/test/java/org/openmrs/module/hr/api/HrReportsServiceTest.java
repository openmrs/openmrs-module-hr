package org.openmrs.module.hr.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.test.SkipBaseSetup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HrReportsServiceTest extends BaseModuleContextSensitiveTest {
    HRReportsService hrReportsService;

    @Before
	public void before() throws Exception {
        executeDataSet("person_test_data.xml");
        executeDataSet("privilege_data.xml");
		executeDataSet("reports_service_test_data.xml");
        hrReportsService = Context.getService(HRReportsService.class);
	}

    @Test
    @SkipBaseSetup
    public void shouldSetUpContext(){
        assertNotNull(Context.getService(HRStaffService.class));
    }

    @Test
    @SkipBaseSetup
    public void shouldGetReportById(){
        Context.authenticate("hrmanager","Hrmanager123");
        assertNotNull("should get report by ID",hrReportsService.getHrReport(1));
        Context.authenticate("admin","Admin123");
    }

    @Test
    @SkipBaseSetup
    public void shouldGetAllReports(){
        Context.authenticate("hrmanager","Hrmanager123");
        assertEquals(2,hrReportsService.getHrReports().size());
        Context.authenticate("admin","Admin123");

    }
}
