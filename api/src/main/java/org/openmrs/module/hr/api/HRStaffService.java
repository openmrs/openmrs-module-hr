package org.openmrs.module.hr.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffAttributeType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRStaffService{

    @Authorized("Manage Staff")
    public void saveStaff(HrStaff staff);

    @Authorized("View Staff")
    public HrStaff getStaffById( int id);

    @Authorized("View Staff")
    public List<HrStaff> getAllStaff(boolean includeAllStaff,boolean includeAllLocations);

    @Authorized("Manage Staff Attribute Types")
    public void saveStaffAttributeType(HrStaffAttributeType staffAttributeType);

    @Authorized("Manage Staff Attribute Types")
	public List<HrStaffAttributeType> getAllStaffAttributeTypes();

    @Authorized("Manage Staff Attribute Types")
    public HrStaffAttributeType getStaffAttributeTypeById( int id);

    @Authorized("Manage Staff Attribute Types")
    public void retireStaffAttributeType(HrStaffAttributeType staffAttributeType,String retireReason);

    @Authorized("Manage Staff Attribute Types")
	public void unretireStaffAttributeType(HrStaffAttributeType staffAttributeType);

    @Authorized("Manage Staff Attribute Types")
	public void purgeStaffAttributeType(HrStaffAttributeType staffAttributeType);

    @Authorized("Manage Staff Attribute Types")
    public HrStaffAttributeType getStaffAttributeTypeByName(String name) ;

}
