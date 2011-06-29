package org.openmrs.module.hr.web.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrIscoCodes;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrStaffAttributeType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StaffAttributeTypeController {
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	/** Success form view name */
	private final String SUCCESS_LIST_VIEW = "/module/hr/admin/staffAttributeTypes";
	private final String SUCCESS_FORM_VIEW = "/module/hr/admin/staffAttributeType";
	
	
	/**
	 * Initially called after the formBackingObject method to get the landing form name  
	 * @return String form view name
	 */
	@RequestMapping(value = "module/hr/admin/staffAttributeTypes.list")
	public String showList(ModelMap model){
		HRService hrService=Context.getService(HRService.class);
		List<HrStaffAttributeType> staffAttributeTypeList= hrService.getAllStaffAttributeTypes();
		model.addAttribute("StaffAttributeTypeList",staffAttributeTypeList);
		return SUCCESS_LIST_VIEW;
	}
	@RequestMapping(value="module/hr/admin/staffAttributeType.form",method=RequestMethod.GET)
	@ModelAttribute("staffAttributeType")
	public String showForm(ModelMap model,@RequestParam(value="staffAttributeTypeId",required=false) Integer staffAttributeTypeId,@ModelAttribute(value="staffAttributeType") HrStaffAttributeType staffAttributeType,Errors errors)
	{
		/*HRService hrService=Context.getService(HRService.class);
		List<HrIscoCodes> iscoCodeList= Context.get
		model.addAttribute("IscoCodeList",iscoCodeList);*/
		return SUCCESS_FORM_VIEW;
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
