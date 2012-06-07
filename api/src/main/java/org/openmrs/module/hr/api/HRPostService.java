package org.openmrs.module.hr.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.module.hr.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
public interface HRPostService {

    @Authorized("Manage Job Titles")
    public List<HrJobTitle> getAllJobTitles();

    @Authorized("Manage Job Titles")
    public void saveJobTitle(HrJobTitle jobTitle);

    @Authorized("Manage Posts")
    public HrPost savePost(HrPost post);

    @Authorized("View Posts")
    public List<HrPost> getAllPosts(boolean includeAllPosts,boolean includeAllLocations);

    @Authorized("Manage Job Titles")
    public void retireJobTitle(HrJobTitle jobTitle,String retireReason);

    @Authorized("Manage Job Titles")
    public void unretireJobTitle(HrJobTitle jobTitle);

    @Authorized("Manage Job Titles")
    public HrJobTitle getJobTitleById( int id);

    @Authorized("Manage Job Titles")
    public List<HrIscoCodes> getAllIscoCodes();

    @Authorized("Manage Job Titles")
	public HrIscoCodes getIscoCodeById( String id) ;


    public String getMostRecentIncumbentForPostbyId(int id);

    @Authorized("View Posts")
    public HrPost getPostById( int id);

    @Authorized("Manage Posts")
	public void retirePost(HrPost post,String reitreReason);

    @Authorized("Manage Posts")
	public void unretirePost(HrPost post);

	public Map<String,Object> getCurrentJobLocationForStaff(int id);

    public List<HrPostHistory> getPostHistoriesForStaff(HrStaff staff);

    public HrPostHistory getPostHistoryById( int id);

    public HrPostHistory getCurrentPostForStaff(int staffId);

    public void savePostHistory(HrPostHistory postHistory);

    @Authorized("Add Assignments")
    public void saveAssignment(HrAssignment assignment);


    public HrAssignment getAssignmentById( int id);

    public List<HrAssignment> getAssignmentsForPostHistory(HrPostHistory postHistory);

    public HrPost wasPostOpen(HrPost post,Date start,Date end);

    public List<HrPost> getPostsByJobTitle(Integer locationId);

    List<HrPost> getOpenPostByJobTitle(Integer locationId);
}
