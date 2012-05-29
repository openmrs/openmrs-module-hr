package org.openmrs.module.hr.api.impl;

import org.openmrs.module.hr.HrIscoCodes;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.api.HRPostService;
import org.openmrs.module.hr.api.db.HRISCOCodeDAO;
import org.openmrs.module.hr.api.db.HRJobTitleDAO;

import java.util.List;
import java.util.Map;

public class HRPostServiceImpl implements HRPostService {

    HRISCOCodeDAO hriscoCodeDAO;
    HRJobTitleDAO hrJobTitleDAO;

    public void setHrJobTitleDAO(HRJobTitleDAO hrJobTitleDAO) {
        this.hrJobTitleDAO = hrJobTitleDAO;
    }

    public void setHriscoCodeDAO(HRISCOCodeDAO hriscoCodeDAO) {
        this.hriscoCodeDAO = hriscoCodeDAO;
    }



    public List<HrJobTitle> getAllJobTitles() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void saveJobTitle(HrJobTitle jobTitle) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public HrPost savePost(HrPost post) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<HrPost> getAllPosts(boolean includeAllPosts, boolean includeAllLocations) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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

    public String getMostRecentIncumbentForPostbyId(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public HrPost getPostById(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void retirePost(HrPost post, String reitreReason) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void unretirePost(HrPost post) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Map<String, Object> getCurrentJobLocationForStaff(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
