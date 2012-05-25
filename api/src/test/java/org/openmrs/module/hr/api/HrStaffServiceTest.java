package org.openmrs.module.hr.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffAttributeType;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import static org.junit.Assert.*;

public class HrStaffServiceTest extends BaseModuleContextSensitiveTest{

    HRStaffService hrStaffService;
    @Before
	public void before() throws Exception {
		initializeInMemoryDatabase();
		executeDataSet("staff_service_test_data.xml");
        hrStaffService = Context.getService(HRStaffService.class);
	}

    @Test
    public void shouldSetUpContext(){
        assertNotNull(Context.getService(HRStaffService.class));
    }

    @Test
    public void shouldGetStaffByID(){
        assertNotNull("should get staff by id",hrStaffService.getStaffById(1));
    }

    @Test
    public void shouldSaveTheStaff(){
        HrStaff hrStaff = hrStaffService.getStaffById(1);
        hrStaff.setGender("Male");
        hrStaffService.saveStaff(hrStaff);
        assertEquals("should save staff","Male",hrStaffService.getStaffById(1).getGender());
    }

    @Test
    public void shouldGetAllStaff(){
        assertEquals("should return all staff", 2, hrStaffService.getAllStaff(true, true).size());
    }

    @Test
    public void shouldGetAllStaffAttributesTypes(){
        assertEquals("should return all staff", 2, hrStaffService.getAllStaffAttributeTypes().size());
    }

    @Test
    public void shouldGetStaffAttributeType(){
        assertNotNull("should get staff attribute type by id",hrStaffService.getStaffAttributeTypeById(1));
        assertNotNull("should get staff attribute type by name",hrStaffService.getStaffAttributeTypeByName("Age"));

    }


    @Test
    public void shouldPurgeHRStaffAttribute(){
        HrStaffAttributeType hrStaffAttributeType = hrStaffService.getStaffAttributeTypeById(1);
        hrStaffService.purgeStaffAttributeType(hrStaffAttributeType);
        assertNull(hrStaffService.getStaffAttributeTypeById(1));
    }

    @Test
    public void shouldRetireOrUnretireStaffAttributeType(){
        HrStaffAttributeType hrStaffAttributeType = hrStaffService.getStaffAttributeTypeById(1);
        hrStaffService.retireStaffAttributeType(hrStaffAttributeType,"Testing");
        assertTrue("should retire attribute type",hrStaffService.getStaffAttributeTypeById(1).isRetired());
        hrStaffService.unretireStaffAttributeType(hrStaffAttributeType);
        assertFalse("should un-retire attribute type",hrStaffService.getStaffAttributeTypeById(1).isRetired());

    }


}
