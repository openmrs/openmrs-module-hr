package org.openmrs.module.hr.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HrReportsServiceTest extends BaseModuleContextSensitiveTest {
    HRReportsService hrReportsService;
    @Before
	public void before() throws Exception {
		initializeInMemoryDatabase();
		executeDataSet("reports_service_test_data.xml");
        hrReportsService = Context.getService(HRReportsService.class);
	}

    @Test
    public void shouldSetUpContext(){
        assertNotNull(Context.getService(HRStaffService.class));
    }

    @Test
    public void shouldGetReportById(){
        assertNotNull("should get report by ID",hrReportsService.getHrReport(1));
    }

    @Test
    public void shouldGetAllReports(){
        assertEquals(2,hrReportsService.getHrReports().size());
    }
}
