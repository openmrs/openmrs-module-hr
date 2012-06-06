package org.openmrs.module.hr.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.GlobalProperty;
import org.openmrs.Privilege;
import org.openmrs.api.APIException;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrStaffAttributeType;
import org.openmrs.module.hr.api.HRStaffService;
import org.openmrs.web.WebConstants;
import org.openmrs.web.taglib.fieldgen.FieldGenHandlerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
		HRStaffService hrStaffService=Context.getService(HRStaffService.class);
		List<HrStaffAttributeType> staffAttributeTypeList= hrStaffService.getAllStaffAttributeTypes();
		model.addAttribute("StaffAttributeTypeList",staffAttributeTypeList);
		String property=Context.getAdministrationService().getGlobalProperty("hr.Staff_Attribute_to_display");
		model.addAttribute("toBeDisplayed",property);
		return SUCCESS_LIST_VIEW;
	}
	@ModelAttribute("staffAttributeType")	
	@RequestMapping(value="module/hr/admin/staffAttributeType.form",method=RequestMethod.GET)
	public HrStaffAttributeType showForm(ModelMap model,@RequestParam(value="staffAttributeTypeId",required=false) Integer staffAttributeTypeId)
	{
		HRStaffService hrStaffService=Context.getService(HRStaffService.class);
		HrStaffAttributeType staffAttributeType;
		if(staffAttributeTypeId!=null){
			staffAttributeType=hrStaffService.getStaffAttributeTypeById(staffAttributeTypeId);
			if(staffAttributeType!=null){
			AdministrationService as=Context.getAdministrationService();
			GlobalProperty gp=as.getGlobalPropertyObject("hr.Staff_Attribute_to_display");
			if(gp!=null){
			if(staffAttributeType.getName().equals(gp.getPropertyValue()))
				model.addAttribute("checked",true);
			}
		  }
		}
			else{
			staffAttributeType=new HrStaffAttributeType();
			}
		List<Privilege> privileges = new ArrayList<Privilege>();
		if (Context.isAuthenticated()) {
			privileges = Context.getUserService().getAllPrivileges();
		}
		Set<String> formats = new TreeSet<String>(FieldGenHandlerFactory.getSingletonInstance().getHandlers().keySet());
		formats.add("java.lang.Character");
		formats.add("java.lang.Integer");
		formats.add("java.lang.Float");
		formats.add("java.lang.Boolean");
		model.addAttribute("privileges",privileges);
		model.addAttribute("formats",formats);
		
		return staffAttributeType;
	}
	/**
	 * All the parameters are optional based on the necessity  
	 * 
	 * @param httpSession
	 * @param anyRequestObject
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="module/hr/admin/staffAttributeType.form",method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpServletRequest request,@ModelAttribute("staffAttributeType") HrStaffAttributeType staffAttributeType, BindingResult errors) {
		HRStaffService hrStaffService=Context.getService(HRStaffService.class);
		List<Privilege> privileges = new ArrayList<Privilege>();
		if (Context.isAuthenticated()) {
			privileges = Context.getUserService().getAllPrivileges();
		}
		Set<String> formats = new TreeSet<String>(FieldGenHandlerFactory.getSingletonInstance().getHandlers().keySet());
		formats.add("java.lang.Character");
		formats.add("java.lang.Integer");
		formats.add("java.lang.Float");
		formats.add("java.lang.Boolean");
		String tbd=request.getParameter("toBeDisplayed");
		boolean toBeDisplayed=false;
		if(tbd==null)
		toBeDisplayed=false;
		else if (tbd.equals("on"))
			toBeDisplayed=true;
		ModelAndView formView=new ModelAndView(SUCCESS_FORM_VIEW);
		formView.addObject("privileges", privileges);
		formView.addObject("formats", formats);
		formView.addObject("checked", toBeDisplayed);
		List<HrStaffAttributeType> staffAttributeTypeList=null;
		AdministrationService as=Context.getAdministrationService();
		GlobalProperty gp=as.getGlobalPropertyObject("hr.Staff_Attribute_to_display");
		if (Context.isAuthenticated()) {
			ModelAndView listView=new ModelAndView("/module/hr/admin/staffAttributeTypes");
			if (request.getParameter("purge") != null) {
				try {
					HrStaffAttributeType sat=hrStaffService.getStaffAttributeTypeById(staffAttributeType.getStaffAttributeTypeId());
					hrStaffService.purgeStaffAttributeType(sat);
					if(gp!=null && gp.getPropertyValue().equals(staffAttributeType.getName())){
						gp.setPropertyValue("");
						as.saveGlobalProperty(gp);
					}
					request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Staff Attribute Type purged successfully");
					staffAttributeTypeList= hrStaffService.getAllStaffAttributeTypes();
					listView.addObject("StaffAttributeTypeList", staffAttributeTypeList);
					listView.addObject("toBeDisplayed",gp.getPropertyValue());
					return listView;
				}
				catch (DataIntegrityViolationException e) {
					request.getSession().setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "error.object.inuse.cannot.purge");
					staffAttributeTypeList= hrStaffService.getAllStaffAttributeTypes();
					listView.addObject("StaffAttributeTypeList", staffAttributeTypeList);
					listView.addObject("toBeDisplayed",gp.getPropertyValue());
					return listView;
				}
				catch (APIException e) {
					request.getSession().setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "error.general: " + e.getLocalizedMessage());
					staffAttributeTypeList= hrStaffService.getAllStaffAttributeTypes();
					listView.addObject("StaffAttributeTypeList", staffAttributeTypeList);
					listView.addObject("toBeDisplayed",gp.getPropertyValue());
					return listView;
				}
			}
			
			if (request.getParameter("retire") != null) {
				String retireReason = request.getParameter("retireReason");
				if (staffAttributeType.getId() != null && !(StringUtils.hasText(retireReason))) {
					errors.reject("retireReason", "general.retiredReason.empty");
					return formView;
				}
				hrStaffService.retireStaffAttributeType(hrStaffService.getStaffAttributeTypeById(staffAttributeType.getStaffAttributeTypeId()), retireReason);
				if(gp!=null && gp.getPropertyValue().equals(staffAttributeType.getName())){
					gp.setPropertyValue("");
					as.saveGlobalProperty(gp);
				}
				request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Staff Attribute Type retired scuccessfully");
				staffAttributeTypeList= hrStaffService.getAllStaffAttributeTypes();
				listView.addObject("StaffAttributeTypeList", staffAttributeTypeList);
				listView.addObject("toBeDisplayed",gp.getPropertyValue());
				return listView;
			}
			else if (request.getParameter("unretire") != null) {
				try {
					hrStaffService.unretireStaffAttributeType(hrStaffService.getStaffAttributeTypeById(staffAttributeType.getStaffAttributeTypeId()));
					request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Staff Attribute Type unretired successfully");
					staffAttributeTypeList= hrStaffService.getAllStaffAttributeTypes();
					listView.addObject("StaffAttributeTypeList", staffAttributeTypeList);
					listView.addObject("toBeDisplayed",gp.getPropertyValue());
					return listView;
				}
				catch (APIException e) {
					request.getSession().setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "error.general: " + e.getLocalizedMessage());
					return formView;
				}
			}
			else if (request.getParameter("save") != null) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name", "error.null");
				if (errors.hasErrors()) {
					return formView;
				}
				else {
					hrStaffService.saveStaffAttributeType(staffAttributeType);
					if(toBeDisplayed && gp!=null){
						gp.setPropertyValue(staffAttributeType.getName());
						as.saveGlobalProperty(gp);
					}
					else if(!toBeDisplayed && gp!=null && gp.getPropertyValue().equals(staffAttributeType.getName())){
						gp.setPropertyValue("");
						as.saveGlobalProperty(gp);
					}
					request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Staff Attribute Type saved Successfully");
					staffAttributeTypeList= hrStaffService.getAllStaffAttributeTypes();
					listView.addObject("StaffAttributeTypeList", staffAttributeTypeList);
					listView.addObject("toBeDisplayed",gp.getPropertyValue());
					return listView;
				}
				
			}
			
			
		}
		return formView;
	}
}
