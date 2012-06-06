package org.openmrs.module.hr.api.db;

import org.openmrs.Location;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrPost;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HRPostDAO {
    public HrPost savePost(HrPost post);

    public void deletePost(HrPost post);

    public List<HrPost> findPostByExample(HrPost post);

    public List<HrPost> getAllPosts(boolean includeAllPosts,boolean includeAllLocations);

    public HrPost getPostById(int id);

    public String getMostRecentIncumbentForPostbyId(int id);

    public Map<String,Object> getCurrentJobLocationForStaff(int id);

    List<HrPost> getPostsForJobTitleAndLocation(Location location, HrJobTitle hrJobTitle);

    List<HrPost> getPostsByJobTitle(Integer locationId);

    public List<HrPost> getOpenPostByJobTitle(Integer locationId);
}
