package org.openmrs.module.hr.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import static org.junit.Assert.*;

public class HrTrainingServiceTest extends BaseModuleContextSensitiveTest {

    HRTrainingService hrTrainingService;

    @Before
    public void setUp() throws Exception {
        hrTrainingService = Context.getService(HRTrainingService.class);
        executeDataSet("person_test_data.xml");
        executeDataSet("training_service_test_data.xml");
    }

    @Test
    public void shouldSetUpContext(){
        assertNotNull(hrTrainingService);
    }

    @Test
    public void shouldGetId(){
        assertNotNull(hrTrainingService.getTrainingById(1));
        assertNotNull(hrTrainingService.getTrainingClassById(1));
    }

    @Test
    public void shouldGetByUniqueId(){
        assertNotNull(hrTrainingService.getTrainingByUniqueId("e4380f28-9538-11e0-1111-8a74a6e487f5"));
        assertNotNull(hrTrainingService.getTrainingClassByUniqueId("e4380f28-9538-11e0-1121-8a74a6e487f5"));
    }

    @Test
    public void shouldGetAll(){
        assertEquals(2,hrTrainingService.getTrainings().size());
        assertEquals(1,hrTrainingService.getTrainingClasses().size());
    }

    @Test
    public void shouldRetire(){
        hrTrainingService.retireTraining(hrTrainingService.getTrainingById(1), "test", Context.getAuthenticatedUser());
        assertTrue(hrTrainingService.getTrainingById(1).isRetired());
        hrTrainingService.unretireTraining(hrTrainingService.getTrainingById(1));
        assertFalse(hrTrainingService.getTrainingById(1).isRetired());
    }

    @Test
    public void shouldGetTrainingClassesFor(){
        assertEquals(2,hrTrainingService.getTrainingHistoryFor(7777701).size());
    }


}
