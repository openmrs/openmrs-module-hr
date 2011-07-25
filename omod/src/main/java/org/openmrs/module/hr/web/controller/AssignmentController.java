package org.openmrs.module.hr.web.controller;

import java.text.NumberFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.propertyeditor.LocationEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("staff")
public class AssignmentController {
	/** Logger for this class and subclasses */
	protected static final Log log = LogFactory.getLog(StaffController.class);
	
	private final String SUCCESS_FORM_VIEW = "/module/hr/manager/assignment";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		NumberFormat nf = NumberFormat.getInstance(Context.getLocale());
		binder.registerCustomEditor(java.lang.Integer.class, new CustomNumberEditor(java.lang.Integer.class, nf, true));
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(Context.getDateFormat(), true, 10));
	}
	
	@RequestMapping(value = "module/hr/manager/assignment.form",method = RequestMethod.GET)
	public String showList(ModelMap model,@RequestParam(required=false,value="assignmentId") Integer assignmentId,@ModelAttribute("staff") HrStaff staff){
		HRService hrService=Context.getService(HRService.class);
		model.addAttribute("assignment", hrService.getAssignmentById(assignmentId));
		return SUCCESS_FORM_VIEW;
	}
}
