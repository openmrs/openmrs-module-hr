package org.openmrs.module.hr.api;

import java.util.Date;
import java.util.List;

import org.openmrs.module.hr.HrAssignment;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.HrPostHistory;
import org.openmrs.module.hr.HrStaff;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HRManagerService {

	public List<HrPostHistory> getPostHistoriesForStaff(HrStaff staff);
	
	public HrAssignment getAssignmentById( int id);
	
	public HrPostHistory getPostHistoryById( int id);
	
	public HrPostHistory getCurrentPostForStaff(int staffId);
	
	public List<HrPost> getOpenPostByJobTitle(Integer locationId);
	
	public List<HrPost> getPostsByJobTitle(Integer locationId);
	
	public void saveAssignment(HrAssignment assignment);
	
	public void savePostHistory(HrPostHistory postHistory);
	
	public List<HrAssignment> getAssignmentsForPostHistory(HrPostHistory postHistory);
	
	public HrPost wasPostOpen(HrPost post,Date start,Date end);
	
}
