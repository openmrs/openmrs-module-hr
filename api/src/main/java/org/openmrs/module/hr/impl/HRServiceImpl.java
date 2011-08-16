package org.openmrs.module.hr.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrAssignment;
import org.openmrs.module.hr.HrIscoCodes;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.HrPostHistory;
import org.openmrs.module.hr.HrReport;
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
	public List<HrPost> getAllPosts(boolean includeAllPosts,boolean includeAllLocations) {
		List<HrPost> list=dao.getAllPosts(includeAllPosts,includeAllLocations);
		return list;
	}
	public void saveStaff(HrStaff staff) {
		dao.saveStaff(staff);
		
	}
	public List<HrStaff> getAllStaff(boolean includeAllStaff,boolean includeAllLocations) {
		return dao.getAllStaff(includeAllStaff,includeAllLocations);
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
	public HrStaffAttributeType getStaffAttributeTypeById(int id) {
		return dao.getStaffAttributeTypeById(id);
	}
	public String getMostRecentIncumbentForPostbyId(int id){
		return dao.getMostRecentIncumbentForPostbyId(id);
	}
	public void retireStaffAttributeType(HrStaffAttributeType staffAttributeType, String reitreReason) {
		dao.saveStaffAttributeType(staffAttributeType);
		
	}
	public void unretireStaffAttributeType(HrStaffAttributeType staffAttributeType) {
		dao.saveStaffAttributeType(staffAttributeType);
		
	}
	public void purgeStaffAttributeType(HrStaffAttributeType staffAttributeType){
		dao.deleteStaffAttributeType(staffAttributeType);
	}
	public HrPost getPostById( int id) {
		return dao.getPostById(id);
	}
	public void retirePost(HrPost post,String reitreReason){
		dao.savePost(post);
	}
	public void unretirePost(HrPost post){
		dao.savePost(post);
	}
	public Map<String,Object> getCurrentJobLocationForStaff(int id){
		return dao.getCurrentJobLocationForStaff(id);
	}
	public HrStaff getStaffById( int id){
		return dao.getStaffById(id);
	}
	public HrStaffAttributeType getStaffAttributeTypeByName(String name) 
	{
		return dao.getStaffAttributeTypeByName(name);
	}
	public List<HrReport> getHrReports() throws DAOException {
		return dao.getHrReports();
	}
	public HrReport getHrReport(Integer reportId) throws DAOException{
		return dao.getHrReport(reportId);
	}
}
