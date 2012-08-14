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

    @Authorized({PrivilegeConstants.MANAGE_JOB_TITLES,PrivilegeConstants.MANAGE_POSTS})
    public List<HrJobTitle> getAllJobTitles();

    @Authorized(PrivilegeConstants.MANAGE_JOB_TITLES)
    public void saveJobTitle(HrJobTitle jobTitle);

    @Authorized(PrivilegeConstants.MANAGE_POSTS)
    public HrPost savePost(HrPost post);

    @Authorized(PrivilegeConstants.MANAGE_POSTS)
    public List<HrPost> getAllPosts(boolean includeAllPosts,boolean includeAllLocations);

    @Authorized(PrivilegeConstants.MANAGE_JOB_TITLES)
    public void retireJobTitle(HrJobTitle jobTitle,String retireReason,User retiredBy);

    @Authorized(PrivilegeConstants.MANAGE_JOB_TITLES)
    public void unretireJobTitle(HrJobTitle jobTitle);

    @Authorized({PrivilegeConstants.MANAGE_JOB_TITLES,PrivilegeConstants.MANAGE_POSTS})
    public HrJobTitle getJobTitleById( int id);

    @Authorized(PrivilegeConstants.MANAGE_JOB_TITLES)
    public List<HrIscoCodes> getAllIscoCodes();

    @Authorized(PrivilegeConstants.MANAGE_JOB_TITLES)
	public HrIscoCodes getIscoCodeById( String id) ;

    @Authorized(PrivilegeConstants.MANAGE_POSTS)
    public String getMostRecentIncumbentForPostbyId(int id);

    @Authorized(PrivilegeConstants.MANAGE_POSTS)
    public HrPost getPostById( int id);

    @Authorized(PrivilegeConstants.MANAGE_POSTS)
	public void retirePost(HrPost post,String reitreReason);

    @Authorized(PrivilegeConstants.MANAGE_POSTS)
	public void unretirePost(HrPost post);

    @Authorized({PrivilegeConstants.VIEW_STAFF})
	public Map<String,Object> getCurrentJobLocationForStaff(int id);

    @Authorized(PrivilegeConstants.MANAGE_POSTS)
    public List<HrPostHistory> getPostHistoriesForStaff(HrStaff staff);

    @Authorized(PrivilegeConstants.MANAGE_POSTS)
    public HrPostHistory getPostHistoryById( int id);

    @Authorized({PrivilegeConstants.MANAGE_ASSIGNMENTS,PrivilegeConstants.VIEW_POSTS})
    public HrPostHistory getCurrentPostForStaff(int staffId);

    @Authorized(PrivilegeConstants.MANAGE_POSTS)
    public void savePostHistory(HrPostHistory postHistory);

    @Authorized(PrivilegeConstants.MANAGE_ASSIGNMENTS)
    public void saveAssignment(HrAssignment assignment);

    @Authorized(PrivilegeConstants.MANAGE_ASSIGNMENTS)
    public HrAssignment getAssignmentById( int id);

    @Authorized(PrivilegeConstants.VIEW_POSTS)
    public List<HrAssignment> getAssignmentsForPostHistory(HrPostHistory postHistory);

    @Authorized(PrivilegeConstants.MANAGE_POSTS)
    public HrPost wasPostOpen(HrPost post,Date start,Date end);

    @Authorized(PrivilegeConstants.MANAGE_POSTS)
    public List<HrPost> getPostsByJobTitle(Integer locationId);

    @Authorized(PrivilegeConstants.MANAGE_POSTS)
    List<HrPost> getOpenPostByJobTitle(Integer locationId);
}
