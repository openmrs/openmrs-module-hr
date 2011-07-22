package org.openmrs.module.hr.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Location;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.listItem.StaffListItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HumanResourcesController {
	
	
		
		/** Logger for this class and subclasses */
		protected final Log log = LogFactory.getLog(getClass());
		
		/** Success form view name */
		
		private final String SUCCESS_LIST_VIEW = "/module/hr/manager/findStaff";
		/**
		 * Initially called after the formBackingObject method to get the landing form name  
		 * @return String form view name
		 */
		@RequestMapping(value = "module/hr/manager/findStaff.list",method=RequestMethod.GET)
		public String showList(ModelMap model,@RequestParam(required=false,value="allstaff") boolean allStaffIncluded,@RequestParam(required=false,value="alllocations") boolean allLocationsIncluded){
			HRService hrService=Context.getService(HRService.class);
			List<HrStaff> staffList=hrService.getAllStaff(allStaffIncluded,allLocationsIncluded);
			List<StaffListItem> staffListItemList=new ArrayList<StaffListItem>();
			PersonService ps=Context.getPersonService();
			for(HrStaff staff:staffList){
				Map<String,Object> jlMap=hrService.getCurrentJobLocationForStaff(staff.getId());
				staffListItemList.add(new StaffListItem(ps.getPerson(staff.getId()), staff, ((Location)(jlMap.get("Location"))).getName(),((HrJobTitle) jlMap.get("JobTitle")).getTitle()));
			}
			model.addAttribute("StaffListItemList",staffListItemList);
			return SUCCESS_LIST_VIEW;
		
		}
		
}

