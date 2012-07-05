package org.openmrs.module.hr.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HrNoteServiceTest extends BaseModuleContextSensitiveTest {

    HRNoteService hrNoteService;

    @Before
    public void setUp() throws Exception {
        hrNoteService = Context.getService(HRNoteService.class);
        executeDataSet("staff_service_test_data.xml");
        executeDataSet("person_test_data.xml");
        executeDataSet("note_service_test_data.xml");
    }

    @Test
    public void shouldSetUpContext(){
        assertNotNull(hrNoteService);
    }

    @Test
    public void shouldGetStaffNoteForId(){
        assertNotNull(hrNoteService.getStaffNoteById(1));
    }

    @Test
    public void shouldGetAllNotesForStaffGivenType(){
        HrStaff hrStaff = Context.getService(HRStaffService.class).getStaffById(7777701);
        assertEquals(1,hrNoteService.getHeadNotesForStaff(hrStaff,"injury").size());
    }


}
