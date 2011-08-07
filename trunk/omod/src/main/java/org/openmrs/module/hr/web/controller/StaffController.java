package org.openmrs.module.hr.web.controller;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;
import org.openmrs.api.ConceptService;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffAttributeType;
import org.openmrs.module.hr.listItem.StaffListItem;
import org.openmrs.module.hr.validator.PersonValidator;
import org.openmrs.propertyeditor.ConceptEditor;
import org.openmrs.propertyeditor.LocationEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class StaffController {
	/** Logger for this class and subclasses */
	protected static final Log log = LogFactory.getLog(StaffController.class);
	
	private final String SUCCESS_LIST_VIEW = "/module/hr/admin/staffList";
	private final String SUCCESS_FORM_VIEW = "/module/hr/admin/staff";


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		NumberFormat nf = NumberFormat.getInstance(Context.getLocale());
		binder.registerCustomEditor(java.lang.Integer.class, new CustomNumberEditor(java.lang.Integer.class, nf, true));
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(Context.getDateFormat(), true, 10));
		binder.registerCustomEditor(Concept.class, "civilStatus", new ConceptEditor());
		binder.registerCustomEditor(Concept.class, "causeOfDeath", new ConceptEditor());
		binder.registerCustomEditor(Location.class, new LocationEditor());
	}
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
			staffListItemList.add(new StaffListItem(ps.getPerson(staff.getId()), staff, ((Location)(jlMap.get("Location")))==null?"":((Location)(jlMap.get("Location"))).getName(),((HrJobTitle) jlMap.get("JobTitle"))==null?"":((HrJobTitle) jlMap.get("JobTitle")).getTitle()));
		}
		model.addAttribute("StaffListItemList",staffListItemList);
		return SUCCESS_LIST_VIEW;
	}
	@ModelAttribute("person")
	public Person showForm(ModelMap model,@RequestParam(required = false, value = "personId") Integer personId)
	{
		Person person=null;
		if(personId!=null){
			person=Context.getPersonService().getPerson(personId);
		}
		else {
			person=new Person();
			if (person.getNames().size() < 1)
				person.addName(new PersonName());
			
			if (person.getAddresses().size() < 1){
				person.addAddress(new PersonAddress());
				
			}
		}
		model.addAttribute("emptyName",new PersonName());
		model.addAttribute("emptyAddress",new PersonAddress());
		return person;
	}
	
	@ModelAttribute("staff")
	@RequestMapping(value="module/hr/admin/staff.form",method=RequestMethod.GET)
	public HrStaff getStaff(ModelMap model,@RequestParam(required = false, value = "personId") Integer personId,@RequestParam(required = false, value = "managerEdit") boolean managerEdit,@RequestParam(required = false, value = "createNewPerson") String createNewPerson) {
		HrStaff staff=null;
		HRService hrService=Context.getService(HRService.class);
		if(personId!=null)
		staff=hrService.getStaffById(personId);
		if(staff==null)
		{
			staff=new HrStaff();
		}
		model.addAttribute("managerEdit",managerEdit);
		if (createNewPerson != null)
			model.addAttribute("createNewPerson", createNewPerson);
		return staff;
	}
	@ModelAttribute("staffAttributeTypes")
	public List<HrStaffAttributeType> getSatffAttributeTypes(){
		return Context.getService(HRService.class).getAllStaffAttributeTypes();
	}
	/**
	 * All the parameters are optional based on the necessity  
	 * 
	 * @param httpSession
	 * @param anyRequestObject
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="module/hr/admin/staff.form",method=RequestMethod.POST)
	public String onSubmit(HttpServletRequest request,ModelMap model,@ModelAttribute("person") Person person, BindingResult errors) {
		HRService hrService=Context.getService(HRService.class);
		if (Context.isAuthenticated()) {
		Concept EmployeeConcept=null;
		ConceptService cs=Context.getConceptService();
		Concept c=cs.getConceptByMapping("Staff status","HR Module");
		if(c!=null){
		Collection<ConceptAnswer> staffStatusAnswers=c.getAnswers();
		if(!staffStatusAnswers.isEmpty()){
		Iterator<ConceptAnswer> staffStatusIterator=staffStatusAnswers.iterator();
		while(staffStatusIterator.hasNext())
			if(((EmployeeConcept=staffStatusIterator.next().getAnswerConcept()).getName().getName().equals("Employee")))
					break;
		}
		}
		//hrService.saveStaff(staff);
		if (person.getDead()) {
			log.debug("Person is dead, so let's make sure there's an Obs for it");
			// need to make sure there is an Obs that represents the person's cause of death, if applicable
			
			String causeOfDeathConceptId = Context.getAdministrationService().getGlobalProperty(
			    "concept.causeOfDeath");
			Concept causeOfDeath = Context.getConceptService().getConcept(causeOfDeathConceptId);
			
			if (causeOfDeath != null) {
				List<Obs> obssDeath = Context.getObsService().getObservationsByPersonAndConcept(person,causeOfDeath);
				if (obssDeath != null) {
					if (obssDeath.size() > 1) {
						log.error("Multiple causes of death (" + obssDeath.size() + ")?  Shouldn't be...");
					} else {
						Obs obsDeath = null;
						if (obssDeath.size() == 1) {
							// already has a cause of death - let's edit it.
							log.debug("Already has a cause of death, so changing it");
							
							obsDeath = obssDeath.iterator().next();
							
						} else {
							// no cause of death obs yet, so let's make one
							log.debug("No cause of death yet, let's create one.");
							
							obsDeath = new Obs();
							obsDeath.setPerson(person);
							obsDeath.setConcept(causeOfDeath);
							Location location = Context.getLocationService().getDefaultLocation();
							// TODO person healthcenter //if ( loc == null ) loc = person.getHealthCenter();
							if (location != null)
								obsDeath.setLocation(location);
							else
								log.error("Could not find a suitable location for which to create this new Obs");
						}
						
						// put the right concept and (maybe) text in this obs
						Concept currCause = person.getCauseOfDeath();
						if (currCause == null) {
							// set to NONE
							log.debug("Current cause is null, attempting to set to NONE");
							String noneConcept = Context.getAdministrationService()
							        .getGlobalProperty("concept.none");
							currCause = Context.getConceptService().getConcept(noneConcept);
						}
						
						if (currCause != null) {
							log.debug("Current cause is not null, setting to value_coded");
							obsDeath.setValueCoded(currCause);
							obsDeath.setValueCodedName(currCause.getName()); // ABKTODO: presume current locale?
							
							Date dateDeath = person.getDeathDate();
							if (dateDeath == null)
								dateDeath = new Date();
							obsDeath.setObsDatetime(dateDeath);
							
							// check if this is an "other" concept - if so, then we need to add value_text
							String otherConcept = Context.getAdministrationService().getGlobalProperty(
							    "concept.otherNonCoded");
							Concept conceptOther = Context.getConceptService().getConcept(otherConcept);
							boolean deathReasonChanged = false;
							if (conceptOther != null) {
								String otherInfo = ServletRequestUtils.getStringParameter(request,
								    "causeOfDeath_other", "");
								if (conceptOther.equals(currCause)) {
									// seems like this is an other concept - let's try to get the "other" field info
									deathReasonChanged = !otherInfo.equals(obsDeath.getValueText());
									log.debug("Setting value_text as " + otherInfo);
									obsDeath.setValueText(otherInfo);
								} else {
									// non empty text value implies concept changed from OTHER NON CODED to NONE
									deathReasonChanged = !otherInfo.equals("");
									log.debug("New concept is NOT the OTHER concept, so setting to blank");
									obsDeath.setValueText("");
								}
							} else {
								log.debug("Don't seem to know about an OTHER concept, so deleting value_text");
								obsDeath.setValueText("");
							}
							boolean shouldSaveObs = (null == obsDeath.getId()) || deathReasonChanged;
							if (shouldSaveObs) {
								if (null == obsDeath.getVoidReason())
									obsDeath.setVoidReason("Changed in demographics editor");
								Context.getObsService().saveObs(obsDeath, obsDeath.getVoidReason());
							}
						} else {
							log.debug("Current cause is still null - aborting mission");
						}
					}
				}
			} else {
				log
				        .debug("Cause of death is null - should not have gotten here without throwing an error on the form.");
			}
			
		}
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
				
				person.addAddress(pa);
				//}
			}
			Iterator<PersonAddress> addresses = person.getAddresses().iterator();
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
		
		// Person Names
		
		objs = person.getNames().toArray();
		for (int i = 0; i < objs.length; i++) {
			if (request.getParameter("names[" + i + "].givenName") == null)
				person.removeName((PersonName) objs[i]);
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
					person.addName(pn);
				}
			}
			Iterator<PersonName> names = person.getNames().iterator();
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
		new PersonValidator().validate(person, errors);
		if (errors.hasErrors()) {
			return SUCCESS_FORM_VIEW;
		}
		Context.getPersonService().savePerson(person);
		HrStaff staff=new HrStaff();
		staff.setStaffId(person.getPersonId());
		staff.setStaffStatus(EmployeeConcept);
		if(staff.getInitialHireDate()==null)
			staff.setInitialHireDate(new Date());
		hrService.saveStaff(staff);
		if(Boolean.valueOf(request.getParameter("managerEdit")).booleanValue())
			return "redirect:/module/hr/manager/staffDemographics.htm?staffId="+staff.getStaffId();
		}
		
		return "redirect:/module/hr/admin/staff.list";
	}
}
