package org.openmrs.module.hr.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HrPostServiceTest extends BaseModuleContextSensitiveTest {
    HRPostService hrPostService;

    @Before
	public void before() throws Exception {
		initializeInMemoryDatabase();
		executeDataSet("post_service_test_data.xml");
        hrPostService = Context.getService(HRPostService.class);
	}

    @Test
    public void shouldSetUpContext(){
        assertNotNull(Context.getService(HRPostService.class));
    }

    @Test
    public void shouldGetAllISCOCodes(){
        assertEquals(2,hrPostService.getAllIscoCodes().size());
    }

    @Test
    public void shouldGetISCOOcdeById(){
        assertNotNull(hrPostService.getIscoCodeById("2"));
    }

    @Test
    public void shouldGetJobTitleById(){
        assertNotNull(hrPostService.getJobTitleById(2));
    }
}
