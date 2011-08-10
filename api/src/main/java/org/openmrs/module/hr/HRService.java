package org.openmrs.module.hr;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HRService {
	
	public List<HrJobTitle> getAllJobTitles();
	
	public void saveJobTitle(HrJobTitle jobTitle);
	
	public void savePost(HrPost post);
	
	public List<HrPost> getAllPosts(boolean includeAllPosts,boolean includeAllLocations);
	
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
	
	public Map<String,Object> getCurrentJobLocationForStaff(int id);
	
	public List<HrStaff> getAllStaff(boolean includeAllStaff,boolean includeAllLocations);
	
	public HrStaff getStaffById( int id);
	
	public HrStaffAttributeType getStaffAttributeTypeByName(String name) ;
	
}
