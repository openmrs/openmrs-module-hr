package org.openmrs.module.hr.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.listItem.StaffListItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaffController {
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	private final String SUCCESS_LIST_VIEW = "/module/hr/admin/staffList";
	private final String SUCCESS_FORM_VIEW = "/module/hr/admin/staff";
	
	/**
	 * Initially called after the formBackingObject method to get the landing form name  
	 * @return String form view name
	 */
	@RequestMapping(value = "module/hr/admin/staff.list",method = RequestMethod.GET)
	public String showList(ModelMap model){
		HRService hrService=Context.getService(HRService.class);
		List<HrStaff> staffList=hrService.getAllStaff();
		List<StaffListItem> staffListItemList=new ArrayList<StaffListItem>();
		PersonService ps=Context.getPersonService();
		for(HrStaff staff:staffList){
			Map<String,String> jlMap=hrService.getCurrentJobLocationForStaff(staff.getId());
			staffListItemList.add(new StaffListItem(ps.getPerson(staff.getId()), staff, jlMap.get("LocationName"), jlMap.get("JobTitle")));
		}
		model.addAttribute("StaffListItemList",staffListItemList);
		return SUCCESS_LIST_VIEW;
	}
	
	@RequestMapping(value="module/hr/admin/staff.form",method=RequestMethod.GET)
	public String showForm()
	{
		return "module/hr/admin/staff";
	}
	/**
	 * All the parameters are optional based on the necessity  
	 * 
	 * @param httpSession
	 * @param anyRequestObject
	 * @param errors
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(HttpSession httpSession,
	                               @ModelAttribute("anyRequestObject") Object anyRequestObject, BindingResult errors) {
		
		if (errors.hasErrors()) {
			// return error view
		}
		
		return SUCCESS_FORM_VIEW;
	}
	
}
