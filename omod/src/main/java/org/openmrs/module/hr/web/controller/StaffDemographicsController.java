package org.openmrs.module.hr.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrStaffAttribute;
import org.openmrs.module.hr.HrStaffAttributeType;
import org.openmrs.module.hr.api.HRStaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"staff","AttributeToDisplay","attrTypes","attributeMap","person"})
public class StaffDemographicsController {
	/** Logger for this class and subclasses */
	protected static final Log log = LogFactory.getLog(StaffController.class);
	
	private final String SUCCESS_VIEW = "/module/hr/manager/staffDemographics";
	
	@RequestMapping(value = "module/hr/manager/staffDemographics.htm",method = RequestMethod.GET)
	public String showPage(ModelMap model,@RequestParam(required=false,value="staffId") Integer staffId){
		HRStaffService hrStaffService=Context.getService(HRStaffService.class);
		if(staffId!=null){
		model.addAttribute("staff",hrStaffService.getStaffById(staffId));
		model.addAttribute("person",Context.getPersonService().getPerson(staffId));
		model.addAttribute("attrTypes",hrStaffService.getAllStaffAttributeTypes());
		Map<String,HrStaffAttribute> attributeMap=new HashMap<String, HrStaffAttribute>();
		for (HrStaffAttribute attribute : hrStaffService.getStaffById(staffId).getActiveAttributes()) {
			attributeMap.put(attribute.getHrStaffAttributeType().getName(), attribute);
		}
		model.addAttribute("attributeMap",attributeMap);
		String property=Context.getAdministrationService().getGlobalProperty("hr.Staff_Attribute_to_display");
		HrStaffAttribute AttributeToDisplay=null;
		HrStaffAttributeType sat=null;
		if(property!=null)
		{
			sat=hrStaffService.getStaffAttributeTypeByName(property);
			if(sat!=null){
			AttributeToDisplay=hrStaffService.getStaffById(staffId).getAttribute(sat);
			}
		}
		if(sat!=null && AttributeToDisplay!=null)
		model.addAttribute("AttributeToDisplay",sat.getName()+":"+AttributeToDisplay.getValue());
		else
			model.addAttribute("AttributeToDisplay","");	
		}
		
		return SUCCESS_VIEW;
	}
}
