package org.openmrs.module.hr.api.impl;

import org.hibernate.criterion.Restrictions;
import org.openmrs.module.hr.*;
import org.openmrs.module.hr.api.HRPostService;
import org.openmrs.module.hr.api.db.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class HRPostServiceImpl implements HRPostService {

    HRISCOCodeDAO hriscoCodeDAO;
    HRJobTitleDAO hrJobTitleDAO;
    HRPostDAO hrPostDAO;
    HRPostHistoryDAO hrPostHistoryDAO;
    HRAssignmentDAO hrAssignmentDAO;

    public void setHrAssignmentDAO(HRAssignmentDAO hrAssignmentDAO) {
        this.hrAssignmentDAO = hrAssignmentDAO;
    }

    public void setHrPostHistoryDAO(HRPostHistoryDAO hrPostHistoryDAO) {
        this.hrPostHistoryDAO = hrPostHistoryDAO;
    }

    public void setHrPostDAO(HRPostDAO hrPostDAO) {
        this.hrPostDAO = hrPostDAO;
    }

    public void setHrJobTitleDAO(HRJobTitleDAO hrJobTitleDAO) {
        this.hrJobTitleDAO = hrJobTitleDAO;
    }

    public void setHriscoCodeDAO(HRISCOCodeDAO hriscoCodeDAO) {
        this.hriscoCodeDAO = hriscoCodeDAO;
    }

    public List<HrPost> getPostsByJobTitle(Integer locationId){
        return hrPostDAO.getPostsByJobTitle(locationId);
    }

    public List<HrPost> getOpenPostByJobTitle(Integer locationId) {
        return hrPostDAO.getOpenPostByJobTitle(locationId);
    }

    public List<HrJobTitle> getAllJobTitles() {
		List<HrJobTitle> list=hrJobTitleDAO.getAllJobTitles();
		return list;
	}
	public void saveJobTitle(HrJobTitle jobTitle) {
		hrJobTitleDAO.saveJobTitle(jobTitle);

	}

    public HrPost savePost(HrPost post) {
		return hrPostDAO.savePost(post);
	}

    public List<HrPost> getAllPosts(boolean includeAllPosts,boolean includeAllLocations) {
		List<HrPost> list=hrPostDAO.getAllPosts(includeAllPosts,includeAllLocations);
		return list;
	}

    public void retireJobTitle(HrJobTitle jobTitle,String retireReason){
		hrJobTitleDAO.saveJobTitle(jobTitle);
	}
	public void unretireJobTitle(HrJobTitle jobTitle) {
		hrJobTitleDAO.saveJobTitle(jobTitle);
	}
	public HrJobTitle getJobTitleById( int id)
	{
		return hrJobTitleDAO.getJobTitleById(id);
	}

    public List<HrIscoCodes> getAllIscoCodes() {
		return hriscoCodeDAO.getAllIscoCodes();
	}
	public HrIscoCodes getIscoCodeById( String id) {
		return hriscoCodeDAO.getIscoCodeById(id);
	}

    public String getMostRecentIncumbentForPostbyId(int id){
		return hrPostDAO.getMostRecentIncumbentForPostbyId(id);
	}

    public HrPost getPostById( int id) {
		return hrPostDAO.getPostById(id);
	}
	public void retirePost(HrPost post,String reitreReason){
		hrPostDAO.savePost(post);
	}
	public void unretirePost(HrPost post){
		hrPostDAO.savePost(post);
	}

    public Map<String,Object> getCurrentJobLocationForStaff(int id){
		return hrPostDAO.getCurrentJobLocationForStaff(id);
	}

    public List<HrPostHistory> getPostHistoriesForStaff(HrStaff staff) {
		return hrPostHistoryDAO.getPostHistoriesForStaff(staff);
	}

    public HrPostHistory getPostHistoryById(int id) {
		return hrPostHistoryDAO.getPostHistoryById(id);
	}

    public HrPostHistory getCurrentPostForStaff(int staffId){
		return hrPostHistoryDAO.getCurrentPostForStaff(staffId);
	}

    public void savePostHistory(HrPostHistory postHistory){
		hrPostHistoryDAO.savePostHistory(postHistory);
	}

    public void saveAssignment(HrAssignment assignment) {
        hrAssignmentDAO.saveAssignment(assignment);
    }

    public HrAssignment getAssignmentById( int id){
        return hrAssignmentDAO.getAssignmentById(id);
    }

    public List<HrAssignment> getAssignmentsForPostHistory(HrPostHistory postHistory){
        return hrAssignmentDAO.getAssignmentsForPostHistory(postHistory);
    }


    public HrPost wasPostOpen(HrPost post,Date start,Date end){
        List<HrPost> criteriaPosts=null;
        if(post!=null && start!=null && end!=null){
            criteriaPosts=hrPostDAO.getPostsForJobTitleAndLocation(post.getLocation(),post.getHrJobTitle());
            List<HrPostHistory> postHistories = hrPostHistoryDAO.getPostHistoriesForPostsInList(criteriaPosts);
            List<HrPost> occupiedPosts=new ArrayList<HrPost>();
            for(HrPostHistory postHistory:postHistories)
            {
                if(postHistory.getStartDate()!=null)
                {
                    if(postHistory.getEndDate()==null){
                        if((start.after(postHistory.getStartDate()) || end.after(postHistory.getStartDate()))){
                            HrPost thispost=getPostById(postHistory.getHrPost().getId());
                            if(!occupiedPosts.contains(thispost))
                                occupiedPosts.add(thispost);
                        }
                    }
                    else{
                        if((end.after(postHistory.getStartDate()))&&(start.before(postHistory.getEndDate()))){
                            HrPost thispost=getPostById(postHistory.getHrPost().getId());
                            if(!occupiedPosts.contains(thispost))
                                occupiedPosts.add(thispost);
                        }
                    }

                }
            }
            criteriaPosts.removeAll(occupiedPosts);
        }
        if(criteriaPosts!=null && criteriaPosts.size()>=1)
            return criteriaPosts.get(0);
        else
            return null;
    }
}
