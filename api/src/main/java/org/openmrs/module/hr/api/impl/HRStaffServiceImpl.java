package org.openmrs.module.hr.api.impl;

import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffAttributeType;
import org.openmrs.module.hr.api.db.HRStaffAttributeDAO;
import org.openmrs.module.hr.api.db.HRStaffAttributeTypeDAO;
import org.openmrs.module.hr.api.db.HRStaffDAO;

import java.util.List;

public class HRStaffServiceImpl implements org.openmrs.module.hr.api.HRStaffService {

    HRStaffAttributeDAO hrStaffAttributeDAO;
    HRStaffDAO hrStaffDAO;
    HRStaffAttributeTypeDAO hrStaffAttributeTypeDAO;

    public void setHrStaffAttributeDAO(HRStaffAttributeDAO hrStaffAttributeDAO) {
        this.hrStaffAttributeDAO = hrStaffAttributeDAO;
    }

    public void setHrStaffAttributeTypeDAO(HRStaffAttributeTypeDAO hrStaffAttributeTypeDAO) {
        this.hrStaffAttributeTypeDAO = hrStaffAttributeTypeDAO;
    }

    public void setHrStaffDAO(HRStaffDAO hrStaffDAO)
	{
		this.hrStaffDAO = hrStaffDAO;
	}

    public void saveStaff(HrStaff staff) {
        hrStaffDAO.saveStaff(staff);
    }

    public HrStaff getStaffById( int id){
		return hrStaffDAO.getStaffById(id);
	}

    public List<HrStaff> getAllStaff(boolean includeAllStaff,boolean includeAllLocations) {
		return hrStaffDAO.getAllStaff(includeAllStaff,includeAllLocations);
	}

    public void saveStaffAttributeType(HrStaffAttributeType staffAttributeType) {
		hrStaffAttributeTypeDAO.saveStaffAttributeType(staffAttributeType);

	}

	public List<HrStaffAttributeType> getAllStaffAttributeTypes() {
		List<HrStaffAttributeType> list=hrStaffAttributeTypeDAO.getAllStaffAttributeTypes();
		return list;
	}

    public HrStaffAttributeType getStaffAttributeTypeById(int id) {
		return hrStaffAttributeTypeDAO.getStaffAttributeTypeById(id);
	}

    public void retireStaffAttributeType(HrStaffAttributeType staffAttributeType, String retireReason) {
		hrStaffAttributeTypeDAO.saveStaffAttributeType(staffAttributeType);

	}

	public void unretireStaffAttributeType(HrStaffAttributeType staffAttributeType) {
		hrStaffAttributeTypeDAO.saveStaffAttributeType(staffAttributeType);

	}

	public void purgeStaffAttributeType(HrStaffAttributeType staffAttributeType){
		hrStaffAttributeTypeDAO.deleteStaffAttributeType(staffAttributeType);
	}


    public HrStaffAttributeType getStaffAttributeTypeByName(String name){
		return hrStaffAttributeTypeDAO.getStaffAttributeTypeByName(name);
	}
}
