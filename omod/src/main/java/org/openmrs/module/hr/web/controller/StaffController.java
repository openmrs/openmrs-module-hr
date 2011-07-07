package org.openmrs.module.hr.web.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;
import org.openmrs.Role;
import org.openmrs.User;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.listItem.StaffListItem;
import org.openmrs.util.OpenmrsConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class StaffController {
	/** Logger for this class and subclasses */
	protected static final Log log = LogFactory.getLog(StaffController.class);
	
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
	@ModelAttribute("person")
	@RequestMapping(value="module/hr/admin/staff.form",method=RequestMethod.GET)
	public Person showForm(ModelMap model,@RequestParam(required = false, value = "personId") Integer personId,@RequestParam(required = false, value = "createNewPerson") String createNewPerson)
	{
		Person person=null;
		if(personId!=null){
			person=Context.getPersonService().getPerson(personId);
		}
		else {
			person=new Person();
		}
		if (person.getNames().size() < 1)
			person.addName(new PersonName());
		
		if (person.getAddresses().size() < 1)
			person.addAddress(new PersonAddress());
		if (createNewPerson != null)
			model.addAttribute("createNewPerson", createNewPerson);
		model.addAttribute("emptyName",new PersonName());
		model.addAttribute("emptyAddress",new PersonAddress());
		return person;
	}
	
	@ModelAttribute("staff")
	public HrStaff getStaff(@RequestParam(required = false, value = "personId") Integer personId) {
		HrStaff staff=null;
		HRService hrService=Context.getService(HRService.class);
		if(personId!=null)
		staff=hrService.getStaffById(personId);
		if(staff==null)
		{
			staff=new HrStaff();
		}
		return staff;
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
	public String onSubmit(HttpSession httpSession,@ModelAttribute("anyRequestObject") Object anyRequestObject, BindingResult errors) {
		
		if (errors.hasErrors()) {
			// return error view
		}
		
		return SUCCESS_FORM_VIEW;
	}
}
