package org.openmrs.module.hr.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRManagerService;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrPostHistory;
import org.openmrs.module.hr.HrStaff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("staff")
public class StaffPositionController {
	/** Logger for this class and subclasses */
	protected static final Log log = LogFactory.getLog(StaffController.class);
	
	private final String SUCCESS_LIST_VIEW = "/module/hr/manager/staffPosition";
	
	@RequestMapping(value = "module/hr/manager/staffPosition.list",method = RequestMethod.GET)
	public String showList(ModelMap model,@ModelAttribute("staff") HrStaff staff){
		HRManagerService hrManagerService=Context.getService(HRManagerService.class);
		List<HrPostHistory> postHistoryList=hrManagerService.getPostHistoriesForStaff(staff);
		model.addAttribute("PostHistories", postHistoryList);
		ConceptService cs=Context.getConceptService();
		List<Concept> staffStatusCurrent=cs.getConceptsByMapping("Staff status current","HR Module");
		if(!staffStatusCurrent.contains(staff.getStaffStatus()))
			model.addAttribute("current",false);
		else {
			model.addAttribute("current",true);
		}
		return SUCCESS_LIST_VIEW;
	}
}
