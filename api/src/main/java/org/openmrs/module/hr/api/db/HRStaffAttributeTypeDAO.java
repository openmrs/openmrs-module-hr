package org.openmrs.module.hr.api.db;

import org.openmrs.module.hr.HrStaffAttributeType;

import java.util.List;

public interface HRStaffAttributeTypeDAO {
    public void saveStaffAttributeType(HrStaffAttributeType staffAttributeType);

	public void deleteStaffAttributeType(HrStaffAttributeType staffAttributeType);

	public List<HrStaffAttributeType> findStaffAttributeTypeByExample(HrStaffAttributeType staffAttributeType);

	public HrStaffAttributeType getStaffAttributeTypeById( int id);

	public List<HrStaffAttributeType> getAllStaffAttributeTypes();

    public HrStaffAttributeType getStaffAttributeTypeByName(String name) ;
}
