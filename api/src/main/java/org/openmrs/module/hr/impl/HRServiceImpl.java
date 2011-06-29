package org.openmrs.module.hr.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrIscoCodes;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffAttributeType;
import org.openmrs.module.hr.db.HRDAO;


public class HRServiceImpl implements HRService{
	private Log log = LogFactory.getLog(getClass());
	private HRDAO dao;
	public void setDao(HRDAO dao)
	{
		this.dao = dao;
	}
	public List<HrJobTitle> getAllJobTitles() {
		List<HrJobTitle> list=dao.getAllJobTitles();
		return list;
	}
	public void saveJobTitle(HrJobTitle jobTitle) {
		dao.saveJobTitle(jobTitle);
		
	}
	public void savePost(HrPost post) {
		dao.savePost(post);
		
	}
	public List<HrPost> getAllPosts() {
		List<HrPost> list=dao.getAllPosts();
		return list;
	}
	public void saveStaff(HrStaff staff) {
		dao.saveStaff(staff);
		
	}
	public void retireJobTitle(HrJobTitle jobTitle,String retireReason){
		dao.saveJobTitle(jobTitle);
	}
	public void unretireJobTitle(HrJobTitle jobTitle) {
		dao.saveJobTitle(jobTitle);
	}
	public HrJobTitle getJobTitleById( int id)
	{
		return dao.getJobTitleById(id);
	}
	public void saveStaffAttributeType(HrStaffAttributeType staffAttributeType) {
		dao.saveStaffAttributeType(staffAttributeType);
		
	}
	public List<HrStaffAttributeType> getAllStaffAttributeTypes() {
		List<HrStaffAttributeType> list=dao.getAllStaffAttributeTypes();
		return list;
	}
	public List<HrIscoCodes> getAllIscoCodes() {
		return dao.getAllIscoCodes();
	}
	public HrIscoCodes getIscoCodeById( String id) {
		return dao.getIscoCodeById(id);
	}
	
	
}
