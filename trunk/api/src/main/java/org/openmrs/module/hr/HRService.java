package org.openmrs.module.hr;

import java.util.List;

public interface HRService {
	
	public List<HrJobTitle> getAllJobTitles();
	
	public void saveJobTitle(HrJobTitle jobTitle);
	
	public void savePost(HrPost post);
	
	public List<HrPost> getAllPosts();
	
	public void saveStaff(HrStaff staff);
	
	public HrJobTitle getJobTitleById( int id);
	
	public void saveStaffAttributeType(HrStaffAttributeType staffAttributeType);
	
	public List<HrStaffAttributeType> getAllStaffAttributeTypes();
	
	public List<HrIscoCodes> getAllIscoCodes();
	
}
