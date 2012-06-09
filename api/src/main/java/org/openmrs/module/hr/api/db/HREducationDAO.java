package org.openmrs.module.hr.api.db;


import org.openmrs.module.hr.HrEducation;
import org.openmrs.module.hr.HrStaff;

import java.util.List;

public interface HREducationDAO {

    public void saveEducation(HrEducation education);

    public void deleteEducation(HrEducation education);

    public List<HrEducation> getEducationForStaff(HrStaff staff);

    public List<HrEducation> findEducationByExample(HrEducation education);

    public HrEducation getEducationById( int id);

}
