package org.openmrs.module.hr.web.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Location;
import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.listItem.StaffListItem;
import org.openmrs.module.hr.validator.StaffValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.SimpleFormController;


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
	@ModelAttribute("person")
	public Person showForm(@RequestParam(required = false, value = "personId") Integer personId,@RequestParam(required = false, value = "createNewPerson") String createNewPerson)
	{
		Person person=null;
		if(personId!=null){
			person=Context.getPersonService().getPerson(personId);
		}
		else {
			person=new Person();
		}
		return person;
	}
	
	@ModelAttribute("staff")
	@RequestMapping(value="module/hr/admin/staff.form",method=RequestMethod.GET)
	public HrStaff getStaff(ModelMap model,@RequestParam(required = false, value = "personId") Integer personId,@RequestParam(required = false, value = "createNewPerson") String createNewPerson) {
		HrStaff staff=null;
		HRService hrService=Context.getService(HRService.class);
		if(personId!=null)
		staff=hrService.getStaffById(personId);
		if(staff==null)
		{
			staff=new HrStaff();
		}
		if (staff.getNames().size() < 1)
			staff.addName(new PersonName());
		
		if (staff.getAddresses().size() < 1)
			staff.addAddress(new PersonAddress());
		if (createNewPerson != null)
			model.addAttribute("createNewPerson", createNewPerson);
		model.addAttribute("emptyName",new PersonName());
		model.addAttribute("emptyAddress",new PersonAddress());
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
	public String onSubmit(HttpServletRequest request,@ModelAttribute("staff") HrStaff staff, BindingResult errors) {
		HRService hrService=Context.getService(HRService.class);
		if (Context.isAuthenticated()) {
		hrService.saveStaff(staff);
		Object[] objs = null;
		String[] add1s = ServletRequestUtils.getStringParameters(request, "address1");
		String[] add2s = ServletRequestUtils.getStringParameters(request, "address2");
		String[] cities = ServletRequestUtils.getStringParameters(request, "cityVillage");
		String[] states = ServletRequestUtils.getStringParameters(request, "stateProvince");
		String[] countries = ServletRequestUtils.getStringParameters(request, "country");
		String[] lats = ServletRequestUtils.getStringParameters(request, "latitude");
		String[] longs = ServletRequestUtils.getStringParameters(request, "longitude");
		String[] pCodes = ServletRequestUtils.getStringParameters(request, "postalCode");
		String[] counties = ServletRequestUtils.getStringParameters(request, "countyDistrict");
		String[] add3s = ServletRequestUtils.getStringParameters(request, "address3");
		String[] addPrefStatus = ServletRequestUtils.getStringParameters(request, "preferred");
		String[] add6s = ServletRequestUtils.getStringParameters(request, "address6");
		String[] add5s = ServletRequestUtils.getStringParameters(request, "address5");
		String[] add4s = ServletRequestUtils.getStringParameters(request, "address4");		
		if (add1s != null || add2s != null || cities != null || states != null || countries != null || lats != null
		        || longs != null || pCodes != null || counties != null || add3s != null || add6s != null
		        || add5s != null || add4s != null) {
			int maxAddrs = 0;
			
			if (add1s != null)
				if (add1s.length > maxAddrs)
					maxAddrs = add1s.length;
			if (add2s != null)
				if (add2s.length > maxAddrs)
					maxAddrs = add2s.length;
			if (cities != null)
				if (cities.length > maxAddrs)
					maxAddrs = cities.length;
			if (states != null)
				if (states.length > maxAddrs)
					maxAddrs = states.length;
			if (countries != null)
				if (countries.length > maxAddrs)
					maxAddrs = countries.length;
			if (lats != null)
				if (lats.length > maxAddrs)
					maxAddrs = lats.length;
			if (longs != null)
				if (longs.length > maxAddrs)
					maxAddrs = longs.length;
			if (pCodes != null)
				if (pCodes.length > maxAddrs)
					maxAddrs = pCodes.length;
			if (counties != null)
				if (counties.length > maxAddrs)
					maxAddrs = counties.length;
			if (add3s != null)
				if (add3s.length > maxAddrs)
					maxAddrs = add3s.length;
			if (add6s != null)
				if (add6s.length > maxAddrs)
					maxAddrs = add6s.length;
			if (add5s != null)
				if (add5s.length > maxAddrs)
					maxAddrs = add5s.length;
			if (add4s != null)
				if (add4s.length > maxAddrs)
					maxAddrs = add4s.length;
			
			log.debug("There appears to be " + maxAddrs + " addresses that need to be saved");
			
			for (int i = 0; i < maxAddrs; i++) {
				PersonAddress pa = new PersonAddress();
				if (add1s.length >= i + 1)
					pa.setAddress1(add1s[i]);
				if (add2s.length >= i + 1)
					pa.setAddress2(add2s[i]);
				if (cities.length >= i + 1)
					pa.setCityVillage(cities[i]);
				if (states.length >= i + 1)
					pa.setStateProvince(states[i]);
				if (countries.length >= i + 1)
					pa.setCountry(countries[i]);
				if (lats.length >= i + 1)
					pa.setLatitude(lats[i]);
				if (longs.length >= i + 1)
					pa.setLongitude(longs[i]);
				if (pCodes.length >= i + 1)
					pa.setPostalCode(pCodes[i]);
				if (counties.length >= i + 1)
					pa.setCountyDistrict(counties[i]);
				if (add3s.length >= i + 1)
					pa.setAddress3(add3s[i]);
				if (addPrefStatus != null && addPrefStatus.length > i)
					pa.setPreferred(new Boolean(addPrefStatus[i]));
				if (add6s.length >= i + 1)
					pa.setAddress6(add6s[i]);
				if (add5s.length >= i + 1)
					pa.setAddress5(add5s[i]);
				if (add4s.length >= i + 1)
					pa.setAddress4(add4s[i]);
				
				staff.addAddress(pa);
				//}
			}
			Iterator<PersonAddress> addresses = staff.getAddresses().iterator();
			PersonAddress currentAddress = null;
			PersonAddress preferredAddress = null;
			while (addresses.hasNext()) {
				currentAddress = addresses.next();
				if (currentAddress.isPreferred()) {
					if (preferredAddress != null) { // if there's a preferred address already exists, make it preferred=false
						preferredAddress.setPreferred(false);
					}
					preferredAddress = currentAddress;
				}
			}
			if ((preferredAddress == null) && (currentAddress != null)) { // No preferred address. Make the last address entry as preferred.
				currentAddress.setPreferred(true);
			}
		}
		
		// Patient Names
		
		objs = staff.getNames().toArray();
		for (int i = 0; i < objs.length; i++) {
			if (request.getParameter("names[" + i + "].givenName") == null)
				staff.removeName((PersonName) objs[i]);
		}
		
		//String[] prefs = request.getParameterValues("preferred");  (unreliable form info)
		String[] gNames = ServletRequestUtils.getStringParameters(request, "givenName");
		String[] mNames = ServletRequestUtils.getStringParameters(request, "middleName");
		String[] fNamePrefixes = ServletRequestUtils.getStringParameters(request, "familyNamePrefix");
		String[] fNames = ServletRequestUtils.getStringParameters(request, "familyName");
		String[] fName2s = ServletRequestUtils.getStringParameters(request, "familyName2");
		String[] fNameSuffixes = ServletRequestUtils.getStringParameters(request, "familyNameSuffix");
		String[] degrees = ServletRequestUtils.getStringParameters(request, "degree");
		String[] namePrefStatus = ServletRequestUtils.getStringParameters(request, "preferred");
		
		if (gNames != null) {
			for (int i = 0; i < gNames.length; i++) {
				if (!"".equals(gNames[i])) { //skips invalid and blank address data box
					PersonName pn = new PersonName();
					if (namePrefStatus != null && namePrefStatus.length > i)
						pn.setPreferred(new Boolean(namePrefStatus[i]));
					if (gNames.length >= i + 1)
						pn.setGivenName(gNames[i]);
					if (mNames.length >= i + 1)
						pn.setMiddleName(mNames[i]);
					if (fNamePrefixes.length >= i + 1)
						pn.setFamilyNamePrefix(fNamePrefixes[i]);
					if (fNames.length >= i + 1)
						pn.setFamilyName(fNames[i]);
					if (fName2s.length >= i + 1)
						pn.setFamilyName2(fName2s[i]);
					if (fNameSuffixes.length >= i + 1)
						pn.setFamilyNameSuffix(fNameSuffixes[i]);
					if (degrees.length >= i + 1)
						pn.setDegree(degrees[i]);
					staff.addName(pn);
				}
			}
			Iterator<PersonName> names = staff.getNames().iterator();
			PersonName currentName = null;
			PersonName preferredName = null;
			while (names.hasNext()) {
				currentName = names.next();
				if (currentName.isPreferred()) {
					if (preferredName != null) { // if there's a preferred name already exists, make it preferred=false
						preferredName.setPreferred(false);
					}
					preferredName = currentName;
				}
			}
			if ((preferredName == null) && (currentName != null)) { // No preferred name. Make the last name entry as preferred.
				currentName.setPreferred(true);
			}
			
		}
		new StaffValidator().validate(staff, errors);
		}
		if (errors.hasErrors()) {
			// return error view
		}
		return SUCCESS_FORM_VIEW+"?personId="+staff.getStaffId();
	}
}
