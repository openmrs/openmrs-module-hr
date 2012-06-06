package org.openmrs.module.hr.api.db;

import org.openmrs.module.hr.HrAssignment;
import org.openmrs.module.hr.HrPostHistory;

import java.util.List;

public interface HRAssignmentDAO {
    public void saveAssignment(HrAssignment assignment);

    public void deleteAssignment(HrAssignment assignment);

    public List<HrAssignment> getAssignmentsForPostHistory(HrPostHistory postHistory);

    public List<HrAssignment> findAssignmentByExample(HrAssignment assignment);

    public HrAssignment getAssignmentById( int id);

}
