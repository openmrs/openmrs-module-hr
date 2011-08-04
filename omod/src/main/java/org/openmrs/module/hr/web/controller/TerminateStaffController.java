package org.openmrs.module.hr.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.hr.HrStaff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("staff")
public class TerminateStaffController {
	/** Logger for this class and subclasses */
	protected static final Log log = LogFactory.getLog(StaffController.class);
	
	private final String SUCCESS_FORM_VIEW = "/module/hr/manager/terminateStaff";
	
	@RequestMapping(value = "module/hr/manager/terminateStaff.form",method = RequestMethod.GET)
	public String showForm(ModelMap model,@ModelAttribute("staff") HrStaff staff){
		return SUCCESS_FORM_VIEW;
		
	}
}
