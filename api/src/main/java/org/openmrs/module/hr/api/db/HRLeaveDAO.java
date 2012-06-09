package org.openmrs.module.hr.api.db;


import org.openmrs.module.hr.HrLeave;

import java.util.List;

public interface HRLeaveDAO {

    public void saveLeave(HrLeave leave);

    public void deleteLeave(HrLeave leave);

    public List<HrLeave> findLeaveByExample(HrLeave leave);

    public HrLeave getLeaveById( int id) ;

}
