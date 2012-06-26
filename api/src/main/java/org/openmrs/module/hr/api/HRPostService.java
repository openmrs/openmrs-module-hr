package org.openmrs.module.hr.api;

import org.openmrs.User;
import org.openmrs.annotation.Authorized;
import org.openmrs.module.hr.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
public interface HRPostService {

    @Authorized({"Manage Job Titles","Manage Posts","Add Post"})
    public List<HrJobTitle> getAllJobTitles();

    @Authorized("Manage Job Titles")
    public void saveJobTitle(HrJobTitle jobTitle);

    @Authorized("Manage Posts")
    public HrPost savePost(HrPost post);

    @Authorized("Manage Posts")
    public List<HrPost> getAllPosts(boolean includeAllPosts,boolean includeAllLocations);

    @Authorized("Manage Job Titles")
    public void retireJobTitle(HrJobTitle jobTitle,String retireReason,User retiredBy);

    @Authorized("Manage Job Titles")
    public void unretireJobTitle(HrJobTitle jobTitle);

    @Authorized({"Manage Job Titles","Add Post"})
    public HrJobTitle getJobTitleById( int id);

    @Authorized("Manage Job Titles")
    public List<HrIscoCodes> getAllIscoCodes();

    @Authorized("Manage Job Titles")
	public HrIscoCodes getIscoCodeById( String id) ;

    @Authorized("Manage Posts")
    public String getMostRecentIncumbentForPostbyId(int id);

    @Authorized({"Manage Posts","Add Post"})
    public HrPost getPostById( int id);

    @Authorized("Manage Posts")
	public void retirePost(HrPost post,String reitreReason);

    @Authorized("Manage Posts")
	public void unretirePost(HrPost post);

    @Authorized({"Find Human Resources","Manage Staff"})
	public Map<String,Object> getCurrentJobLocationForStaff(int id);

    @Authorized("Add Post")
    public List<HrPostHistory> getPostHistoriesForStaff(HrStaff staff);

    @Authorized("Add Post")
    public HrPostHistory getPostHistoryById( int id);

    @Authorized({"Add Assignments","Add Post","View Posts"})
    public HrPostHistory getCurrentPostForStaff(int staffId);

    @Authorized("Add Post")
    public void savePostHistory(HrPostHistory postHistory);

    @Authorized("Add Assignments")
    public void saveAssignment(HrAssignment assignment);

    @Authorized("Add Assignments")
    public HrAssignment getAssignmentById( int id);

    @Authorized("View Posts")
    public List<HrAssignment> getAssignmentsForPostHistory(HrPostHistory postHistory);

    @Authorized("Add Post")
    public HrPost wasPostOpen(HrPost post,Date start,Date end);

    @Authorized("Add Post")
    public List<HrPost> getPostsByJobTitle(Integer locationId);

    @Authorized("Add Post")
    List<HrPost> getOpenPostByJobTitle(Integer locationId);
}
