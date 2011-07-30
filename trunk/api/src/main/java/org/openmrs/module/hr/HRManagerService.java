package org.openmrs.module.hr;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HRManagerService {

	public List<HrPostHistory> getPostHistoriesForStaff(HrStaff staff);
	
	public HrAssignment getAssignmentById( int id);
	
	public HrPostHistory getPostHistoryById( int id);
	
	public HrPostHistory getCurrentPostForStaff(int staffId);
	
	public List<HrPost> getOpenPostByJobTitle();
}
