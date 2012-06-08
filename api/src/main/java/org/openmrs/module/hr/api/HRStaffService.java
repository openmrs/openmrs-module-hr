package org.openmrs.module.hr.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffAttributeType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRStaffService{

    @Authorized({"Manage Staff","Manage Staff Attribute Types","Add Post"})
    public void saveStaff(HrStaff staff);

    @Authorized({"View Staff","View Staff Demographics"})
    public HrStaff getStaffById( int id);

    @Authorized({"View Staff","Find Human Resources","Manage Staff Attribute Types","View Staff Demographics"})
    public List<HrStaff> getAllStaff(boolean includeAllStaff,boolean includeAllLocations);

    @Authorized("Manage Staff Attribute Types")
    public void saveStaffAttributeType(HrStaffAttributeType staffAttributeType);

    @Authorized({"Manage Staff Attribute Types","View Staff Demographics","Manage Staff"})
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
