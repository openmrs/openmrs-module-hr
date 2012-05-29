package org.openmrs.module.hr.api;

import org.openmrs.module.hr.HrIscoCodes;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrPost;

import java.util.List;
import java.util.Map;

public interface HRPostService {

    public List<HrJobTitle> getAllJobTitles();

    public void saveJobTitle(HrJobTitle jobTitle);

    public HrPost savePost(HrPost post);

    public List<HrPost> getAllPosts(boolean includeAllPosts,boolean includeAllLocations);

    public void retireJobTitle(HrJobTitle jobTitle,String retireReason);

    public void unretireJobTitle(HrJobTitle jobTitle);

    public HrJobTitle getJobTitleById( int id);

    public List<HrIscoCodes> getAllIscoCodes();

	public HrIscoCodes getIscoCodeById( String id) ;

    public String getMostRecentIncumbentForPostbyId(int id);

    public HrPost getPostById( int id);

	public void retirePost(HrPost post,String reitreReason);

	public void unretirePost(HrPost post);

	public Map<String,Object> getCurrentJobLocationForStaff(int id);

}
