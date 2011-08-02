package org.openmrs.module.hr.web.controller;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Location;
import org.openmrs.LocationTag;
import org.openmrs.api.ConceptService;
import org.openmrs.api.LocationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRManagerService;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrAssignment;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.HrPostHistory;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.propertyEditor.HrPostEditor;
import org.openmrs.module.hr.propertyEditor.HrPostHistoryEditor;
import org.openmrs.propertyeditor.ConceptEditor;
import org.openmrs.propertyeditor.LocationEditor;
import org.openmrs.web.WebConstants;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
@Controller
@SessionAttributes("staff")
public class PostHistoryController {
	/** Logger for this class and subclasses */
	protected static final Log log = LogFactory.getLog(StaffController.class);
	
	private final String SUCCESS_FORM_VIEW = "/module/hr/manager/postHistory";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		NumberFormat nf = NumberFormat.getInstance(Context.getLocale());
		binder.registerCustomEditor(java.lang.Integer.class, new CustomNumberEditor(java.lang.Integer.class, nf, true));
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(Context.getDateFormat(), true, 10));
		binder.registerCustomEditor(org.openmrs.Concept.class, new ConceptEditor());
		binder.registerCustomEditor(Location.class, new LocationEditor());
		binder.registerCustomEditor(HrPost.class, new HrPostEditor());
	}
	
	@RequestMapping(value = "module/hr/manager/postHistory.form",method = RequestMethod.GET)
	@ModelAttribute("postHistory")
	public HrPostHistory showList(ModelMap model,@RequestParam(required=false,value="alllocations") boolean includeAllLocations,@RequestParam(required=false,value="addprev") boolean addprev,@RequestParam(required=false,value="postHistoryId") Integer postHistoryId,@ModelAttribute("staff") HrStaff staff){
		return prepareModel(postHistoryId,model,staff,addprev,includeAllLocations);
		
	}
	@RequestMapping(value = "module/hr/manager/postHistory.form",method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request,ModelMap model,@ModelAttribute("postHistory") HrPostHistory postHistory,BindingResult errors,@ModelAttribute("staff") HrStaff staff) throws ParseException {
		String actionString=request.getParameter("actionString");
		HRManagerService hrManagerService=Context.getService(HRManagerService.class);
		boolean allLocations=Boolean.getBoolean(request.getParameter("alllocations"));
		if(actionString.equals("createNew"))
		{
			ValidationUtils.rejectIfEmpty(errors,"startDate","error.null");
			String vacateDateString=request.getParameter("vacateEndDate");
			Date vacateEndDate=null;
			if(vacateDateString!=""){
			vacateEndDate=(new SimpleDateFormat("dd/MM/yyyy")).parse(vacateDateString);
			}
			ConceptService cs=Context.getConceptService();
			String vacateEndReasonString=request.getParameter("vacateEndReason");
			Concept vacateEndReason=null;
			if(vacateEndReasonString!="")
			vacateEndReason=cs.getConcept(Integer.parseInt(vacateEndReasonString));;
			String vacateEndReasonText=request.getParameter("vacateEndReasonText")==""?null:request.getParameter("vacateEndReasonText");
			if(vacateEndDate==null)
				errors.reject("vacateEndDate","vacate end date cannot be null");
			if(vacateEndReason==null)
				errors.reject("vacateEndReason","vacate end reason cannot be null");
			if(vacateEndReason!=null){
				if(vacateEndReasonString.endsWith(":"))
					if(vacateEndReasonText==null)
						errors.reject("vacateEndReasonText", "enter a valid vacate end reason text");
			}
			if(errors.hasErrors()){
				prepareModel(postHistory.getPostHistoryId(), model, staff, false,allLocations);
				return SUCCESS_FORM_VIEW;
			}
			postHistory.setHrStaff(staff);
			HrPostHistory currentPosthistory=hrManagerService.getCurrentPostForStaff(staff.getStaffId());
			if(currentPosthistory!=null){
			List<HrAssignment> assignmentsUnder=hrManagerService.getAssignmentsForPostHistory(currentPosthistory);
			Iterator<HrAssignment> iter=assignmentsUnder.iterator();
			while(iter.hasNext())
			{
				HrAssignment assignment=iter.next();
				assignment.setEndDate(vacateEndDate);
				assignment.setEndReason(vacateEndReason);
				assignment.setEndReasonOther(vacateEndReasonText);
				hrManagerService.saveAssignment(assignment);
			}
			HrPost post=currentPosthistory.getHrPost();
			List<Concept> concepts=cs.getConceptsByMapping("Post status current","HR Module");
			Concept openPost=null;
			if(concepts!=null){
			Iterator<Concept> caliter=concepts.iterator();
			while(caliter.hasNext())
				if((openPost=caliter.next()).getName().getName().equals("Open"))
					break;
			}
			post.setStatus(openPost);
			Context.getService(HRService.class).savePost(post);
			currentPosthistory.setEndDate(vacateEndDate);
			currentPosthistory.setEndReason(vacateEndReason);
			currentPosthistory.setEndReasonOther(vacateEndReasonText);
			hrManagerService.savePostHistory(currentPosthistory);
			}
			hrManagerService.savePostHistory(postHistory);
		}
		else if(actionString.equals("addprev"))
		{
			ValidationUtils.rejectIfEmpty(errors,"startDate","error.null");
			ValidationUtils.rejectIfEmpty(errors,"endDate","error.null");
			ValidationUtils.rejectIfEmpty(errors,"endReason","error.null");
			if(postHistory.getEndReason()!=null){
			if(postHistory.getEndReason().getName().getName().endsWith(":"))
				if(postHistory.getEndReasonOther()==null)
					ValidationUtils.rejectIfEmpty(errors,"endReasonOther","error.null");
			}
			if(postHistory.getStartDate()!=null && postHistory.getEndDate()!=null){
			if(postHistory.getStartDate().after(postHistory.getEndDate()));
			ValidationUtils.rejectIfEmpty(errors,"endDate","End Date cannot be before start date");
			}
			if(errors.hasErrors()){
				prepareModel(postHistory.getPostHistoryId(), model, staff, true,allLocations);
				return SUCCESS_FORM_VIEW;
			}
			postHistory.setHrStaff(staff);
			hrManagerService.savePostHistory(postHistory);
			
		}
		else if(actionString.equals("endPostHistory"))
		{
			HrPostHistory postHistoryInstance=hrManagerService.getPostHistoryById(postHistory.getPostHistoryId());
			ValidationUtils.rejectIfEmpty(errors,"endDate","error.null");
			ValidationUtils.rejectIfEmpty(errors,"endReason","error.null");
			if(postHistory.getEndReason()!=null){
			if(postHistory.getEndReason().getName().getName().endsWith(":"))
				if(postHistory.getEndReasonOther()==null)
					ValidationUtils.rejectIfEmpty(errors,"endReasonOther","error.null");
			}
			if(postHistory.getStartDate()!=null && postHistory.getEndDate()!=null){
			if(postHistory.getStartDate().after(postHistory.getEndDate()));
			ValidationUtils.rejectIfEmpty(errors,"endDate","End Date cannot be before start date");
			}
			if(errors.hasErrors()){
				prepareModel(postHistory.getPostHistoryId(), model, staff, false,allLocations);
				return SUCCESS_FORM_VIEW;
			}
			postHistoryInstance.setEndDate(postHistory.getEndDate());
			postHistoryInstance.setEndReason(postHistory.getEndReason());
			postHistoryInstance.setEndReasonOther(postHistory.getEndReasonOther());
			hrManagerService.savePostHistory(postHistoryInstance);
			List<HrAssignment> assignmentsUnder=hrManagerService.getAssignmentsForPostHistory(postHistoryInstance);
			Iterator<HrAssignment> iter=assignmentsUnder.iterator();
			while(iter.hasNext())
			{
				HrAssignment assignment=iter.next();
				assignment.setEndDate(postHistory.getEndDate());
				assignment.setEndReason(postHistory.getEndReason());
				assignment.setEndReasonOther(postHistory.getEndReasonOther());
				hrManagerService.saveAssignment(assignment);
			}
			HrPost post=postHistory.getHrPost();
			ConceptService cs=Context.getConceptService();
			List<Concept> concepts=cs.getConceptsByMapping("Post status current","HR Module");
			Concept openPost=null;
			if(concepts!=null){
			Iterator<Concept> caliter=concepts.iterator();
			while(caliter.hasNext())
				if((openPost=caliter.next()).getName().getName().equals("Open"))
					break;
			}
			post.setStatus(openPost);
			

		}
		request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Position saved successfully");
		return "redirect:/module/hr/manager/staffPosition.list";
	}
	private HrPostHistory prepareModel(Integer postHistoryId,ModelMap model,HrStaff staff,boolean addprev,boolean includeAllLocations){
		HRManagerService hrManagerService=Context.getService(HRManagerService.class);
		ConceptService cs=Context.getConceptService();
		HrPostHistory postHistory;
		if(postHistoryId==null||postHistoryId==0){
		LocationService locService=Context.getLocationService();
		List<Location> locationList=new ArrayList<Location>();
		if(!includeAllLocations){
		LocationTag HrManagedLocations=locService.getLocationTagByName("HR Managed");
		if(HrManagedLocations!=null){
			locationList=locService.getLocationsByTag(HrManagedLocations);
		}
		}
		else {
			locationList=locService.getAllLocations();
		}
		model.addAttribute("locationList",locationList);
		if(addprev==false){
		model.addAttribute("createNew",true);
		List<HrPost> postList=hrManagerService.getOpenPostByJobTitle();
		model.addAttribute("postList",postList);
		}
		else
		{
			model.addAttribute("addprev",true);
			List<HrPost> postList=hrManagerService.getPostsByJobTitle();
			model.addAttribute("postList",postList);
			
		}
		postHistory=new HrPostHistory();
		}
		else {
			postHistory=hrManagerService.getPostHistoryById(postHistoryId);
		}
		Concept endReason=cs.getConceptByMapping("Post history end reason","HR Module");
		Collection<ConceptAnswer> postHistoryEndReasons;
		if(endReason!=null)
			postHistoryEndReasons=endReason.getAnswers();
		else {
			postHistoryEndReasons=new ArrayList<ConceptAnswer>();
		}
		model.addAttribute("EndReasons",postHistoryEndReasons);
		return postHistory;
		
	}
}
