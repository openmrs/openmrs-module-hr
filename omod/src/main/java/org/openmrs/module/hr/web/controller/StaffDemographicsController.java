package org.openmrs.module.hr.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"staff","person"})
public class StaffDemographicsController {
	/** Logger for this class and subclasses */
	protected static final Log log = LogFactory.getLog(StaffController.class);
	
	private final String SUCCESS_VIEW = "/module/hr/manager/staffDemographics";
	
	@RequestMapping(value = "module/hr/manager/staffDemographics.htm",method = RequestMethod.GET)
	public String showPage(ModelMap model,@RequestParam(required=false,value="staffId") Integer staffId){
		model.addAttribute("staff",Context.getService(HRService.class).getStaffById(staffId));
		model.addAttribute("person",Context.getPersonService().getPerson(staffId));
		return SUCCESS_VIEW;
	}
}
