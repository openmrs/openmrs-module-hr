package org.openmrs.module.hr.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrCompetency;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import static org.junit.Assert.*;

public class HrCompetencyServiceTest extends BaseModuleContextSensitiveTest {

    HRCompetencyService hrCompetencyService;

    @Before
    public void SetUp() throws Exception {
        hrCompetencyService = Context.getService(HRCompetencyService.class);
        executeDataSet("person_test_data.xml");
        executeDataSet("staff_service_test_data.xml");
        executeDataSet("competency_service_test_data.xml");

    }

    @Test
    public void shouldSetupContext(){
        assertNotNull(Context.getService(HRCompetencyService.class));
    }

    @Test
    public void shouldGetCompetencyById(){
        assertNotNull(hrCompetencyService.getCompetencyById(1));
    }

    @Test
    public void shouldGetAllCompetencies(){
        assertEquals(2,hrCompetencyService.getCompetencies().size());
    }

    @Test
    public void shouldRetireUnretireCompetency(){
        HrCompetency hrCompetency = hrCompetencyService.getCompetencyById(1);
        hrCompetencyService.retireCompetency(hrCompetency,"test",Context.getAuthenticatedUser());
        assertTrue(hrCompetencyService.getCompetencyById(1).isRetired());
        hrCompetencyService.unretireCompetency(hrCompetency);
        assertFalse(hrCompetencyService.getCompetencyById(1).isRetired());
    }

    @Test
    public void shouldSaveCompetency(){
        HrCompetency competency = new HrCompetency();
        competency.setCategory("test");
        competency.setCompetencyId(13);
        competency.setEditPrivilege("test");
        competency.setLevels("test");
        hrCompetencyService.saveCompetency(competency);
        assertNotNull(hrCompetencyService.getCompetencyById(13));

    }

    @Test
    public void shouldGetEvaluationById(){
        assertNotNull(hrCompetencyService.getEvaluationById(1));
    }


}
