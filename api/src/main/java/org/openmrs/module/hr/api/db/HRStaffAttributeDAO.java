package org.openmrs.module.hr.api.db;

import org.openmrs.module.hr.HrStaffAttribute;

import java.util.List;

public interface HRStaffAttributeDAO {

    public void saveStaffAttribute(HrStaffAttribute staffAttribute);

    public void deleteStaffAttribute(HrStaffAttribute staffAttribute);

    public List<HrStaffAttribute> findStaffAttributeByExample(HrStaffAttribute staffAttribute);

    public HrStaffAttribute getStaffAttributeById(int id);


}
