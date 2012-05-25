package org.openmrs.module.hr.api.db;

import org.openmrs.module.hr.HrStaff;

import java.util.List;

public interface HRStaffDAO {
    public void saveStaff(HrStaff staff);

    public HrStaff getStaffById( int id);

    List<HrStaff> getAllStaff(boolean includeAllStaff, boolean includeAllLocations);

    public void deleteStaff(HrStaff staff);

	public List<HrStaff> findStaffByExample(HrStaff staff);
}
