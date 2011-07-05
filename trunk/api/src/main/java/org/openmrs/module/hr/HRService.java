package org.openmrs.module.hr;

import java.text.ParseException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HRService {
	
	public List<HrJobTitle> getAllJobTitles();
	
	public void saveJobTitle(HrJobTitle jobTitle);
	
	public void savePost(HrPost post);
	
	public List<HrPost> getAllPosts();
	
	public void retireJobTitle(HrJobTitle jobTitle,String retireReason);
	
	public void unretireJobTitle(HrJobTitle jobTitle);
	
	public void saveStaff(HrStaff staff);
	
	public HrJobTitle getJobTitleById( int id);
	
	public void saveStaffAttributeType(HrStaffAttributeType staffAttributeType);
	
	public List<HrStaffAttributeType> getAllStaffAttributeTypes();
	
	public List<HrIscoCodes> getAllIscoCodes();
	
	public HrIscoCodes getIscoCodeById( String id) ;
	
	public HrStaffAttributeType getStaffAttributeTypeById( int id);
	
	public String getMostRecentIncumbentForPostbyId(int id);
	
	public void retireStaffAttributeType(HrStaffAttributeType staffAttributeType,String reitreReason);
	
	public void unretireStaffAttributeType(HrStaffAttributeType staffAttributeType);
	
	public void purgeStaffAttributeType(HrStaffAttributeType staffAttributeType);
	
	public HrPost getPostById( int id);
	
	public void retirePost(HrPost post,String reitreReason);
	
	public void unretirePost(HrPost post);
}
