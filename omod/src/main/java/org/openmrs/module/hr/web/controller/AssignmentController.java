package org.openmrs.module.hr.web.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
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
import org.openmrs.module.hr.HrAssignment;
import org.openmrs.module.hr.HrPostHistory;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.propertyEditor.HrPostHistoryEditor;
import org.openmrs.propertyeditor.ConceptEditor;
import org.openmrs.propertyeditor.LocationEditor;
import org.openmrs.util.OpenmrsConstants;
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
public class AssignmentController {
	/** Logger for this class and subclasses */
	protected static final Log log = LogFactory.getLog(StaffController.class);
	
	private final String SUCCESS_FORM_VIEW = "/module/hr/manager/assignment";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		NumberFormat nf = NumberFormat.getInstance(Context.getLocale());
		binder.registerCustomEditor(java.lang.Integer.class, new CustomNumberEditor(java.lang.Integer.class, nf, true));
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(Context.getDateFormat(), true, 10));
		binder.registerCustomEditor(org.openmrs.Concept.class, new ConceptEditor());
		binder.registerCustomEditor(Location.class, new LocationEditor());
		binder.registerCustomEditor(HrPostHistory.class, new HrPostHistoryEditor());
	}
	
	@RequestMapping(value = "module/hr/manager/assignment.form",method = RequestMethod.GET)
	@ModelAttribute("assignment")
	public HrAssignment showForm(ModelMap model,@RequestParam(required=false,value="assignmentId") Integer assignmentId,@RequestParam(required=false,value="addprev") boolean addprev,@ModelAttribute("staff") HrStaff staff){
		return prepareModel(assignmentId,model,staff,addprev);
		
	}
	@RequestMapping(value = "module/hr/manager/assignment.form",method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request,ModelMap model,@ModelAttribute("assignment") HrAssignment assignment,BindingResult errors,@ModelAttribute("staff") HrStaff staff) {
		String actionString=request.getParameter("actionString");
		HRManagerService hrManagerService=Context.getService(HRManagerService.class);		
		if(actionString.equals("createNew"))
		{
			ValidationUtils.rejectIfEmpty(errors,"startDate","error.null");
			HrPostHistory currentPosthistory=hrManagerService.getCurrentPostForStaff(staff.getStaffId());
			if(assignment.getStartDate()!=null){
				if(currentPosthistory!=null){
				if(assignment.getStartDate().before(currentPosthistory.getStartDate()))
					errors.reject("startDateInvalid","Start Date cannot be before post start date");
				}
			}
			if(errors.hasErrors()){
				prepareModel(assignment.getAssignmentId(), model, staff, true);
				return SUCCESS_FORM_VIEW;
			}
			if(currentPosthistory!=null){
			assignment.setHrPostHistory(currentPosthistory);
			assignment.setStartDate(currentPosthistory.getStartDate());
			hrManagerService.saveAssignment(assignment);
			}
		}
		else if(actionString.equals("addprev"))
		{
			ValidationUtils.rejectIfEmpty(errors,"startDate","error.null");
			ValidationUtils.rejectIfEmpty(errors,"endDate","error.null");
			ValidationUtils.rejectIfEmpty(errors,"endReason","error.null");
			if(assignment.getEndReason()!=null){
			if(assignment.getEndReason().getName().getName().endsWith(":"))
				if(assignment.getEndReasonOther()==null)
					ValidationUtils.rejectIfEmpty(errors,"endReasonOther","error.null");
			}
			if(assignment.getStartDate()!=null && assignment.getEndDate()!=null){
			if(assignment.getStartDate().after(assignment.getEndDate()))
			ValidationUtils.rejectIfEmpty(errors,"endDate","End Date cannot be before start date");
			HrPostHistory thisPostHistory=hrManagerService.getPostHistoryById(assignment.getHrPostHistory().getPostHistoryId());
			if(!((thisPostHistory.getStartDate().before(assignment.getStartDate())||thisPostHistory.getStartDate().equals(assignment.getStartDate())) && (thisPostHistory.getEndDate().after(assignment.getEndDate()) || thisPostHistory.getEndDate().equals(assignment.getEndDate()))))
				errors.reject("startEnd","Start and end Date of assignment invalid for this post");
			}
			if(errors.hasErrors())
			{
				prepareModel(assignment.getAssignmentId(), model, staff, true);
				return SUCCESS_FORM_VIEW;
			}
			hrManagerService.saveAssignment(assignment);
		}
		else if(actionString.equals("endAssignment"))
		{
			HrAssignment assignmentInstance=hrManagerService.getAssignmentById(assignment.getId());
			ValidationUtils.rejectIfEmpty(errors,"endDate","error.null");
			ValidationUtils.rejectIfEmpty(errors,"endReason","error.null");
			if(assignment.getEndReason()!=null){
			if(assignment.getEndReason().getName().getName().endsWith(":"))
				if(assignment.getEndReasonOther()==null)
					ValidationUtils.rejectIfEmpty(errors,"endReasonOther","error.null");
			}
			if(assignment.getStartDate()!=null && assignment.getEndDate()!=null){
				if(assignment.getStartDate().after(assignment.getEndDate()))
				ValidationUtils.rejectIfEmpty(errors,"endDate","End Date cannot be before start date");
			}
			if(errors.hasErrors()){
				prepareModel(assignment.getAssignmentId(), model, staff, false);
				return SUCCESS_FORM_VIEW;
			}
			assignmentInstance.setEndDate(assignment.getEndDate());
			assignmentInstance.setEndReason(assignment.getEndReason());
			assignmentInstance.setEndReasonOther(assignment.getEndReasonOther());
			hrManagerService.saveAssignment(assignmentInstance);
		}
		request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Assignment saved successfully");
		return "redirect:/module/hr/manager/staffPosition.list";
	}
	private HrAssignment prepareModel(Integer assignmentId,ModelMap model,HrStaff staff,boolean addprev){
		HRManagerService hrManagerService=Context.getService(HRManagerService.class);
		HrAssignment assignment;
		if(assignmentId==null||assignmentId==0){
			LocationService locService=Context.getLocationService();
			List<Location> locationList=new ArrayList<Location>();
			LocationTag HrManagedLocations=locService.getLocationTagByName("HR Managed");
			if(HrManagedLocations!=null){
				locationList=locService.getLocationsByTag(HrManagedLocations);
			}
			model.addAttribute("locationList",locationList);
			ConceptService cs=Context.getConceptService();
			Concept workSchedule=cs.getConceptByMapping("Work Schedule","HR Module");
			Collection<ConceptAnswer> workScheduleAnswers;
			if(workSchedule!=null)
				workScheduleAnswers=workSchedule.getAnswers();
			else {
				workScheduleAnswers=new ArrayList<ConceptAnswer>();
			}
			model.addAttribute("workScheduleAnswers",workScheduleAnswers);
			if(addprev==false){
				model.addAttribute("createNew",true);	
				HrPostHistory postHistory= hrManagerService.getCurrentPostForStaff(staff.getStaffId());
				model.addAttribute("currentPost",postHistory);
			}
			else{
				model.addAttribute("addprev",true);
				List<HrPostHistory> postHistories=hrManagerService.getPostHistoriesForStaff(staff);
				HrPostHistory postHistorytobedel=null;
				Iterator<HrPostHistory> iter=postHistories.iterator();
				while(iter.hasNext()){
					HrPostHistory ph;
					if((ph=iter.next()).getEndDate()==null)
						postHistorytobedel=ph;
						break;
				}
				postHistories.remove(postHistorytobedel);
				model.addAttribute("postHistories",postHistories);
				Concept endReason=cs.getConceptByMapping("Post history end reason","HR Module");
				Collection<ConceptAnswer> postHistoryEndReasons;
				if(endReason!=null)
					postHistoryEndReasons=endReason.getAnswers();
				else {
					postHistoryEndReasons=new ArrayList<ConceptAnswer>();
				}
				model.addAttribute("EndReasons",postHistoryEndReasons);
			}
				
			assignment=new HrAssignment();
		}
		else {
			assignment= hrManagerService.getAssignmentById(assignmentId);
			ConceptService cs=Context.getConceptService();
			Concept endReason=cs.getConceptByMapping("Post history end reason","HR Module");
			Collection<ConceptAnswer> postHistoryEndReasons;
			if(endReason!=null)
				postHistoryEndReasons=endReason.getAnswers();
			else {
				postHistoryEndReasons=new ArrayList<ConceptAnswer>();
			}
			model.addAttribute("EndReasons",postHistoryEndReasons);
			Concept workSchedule=cs.getConceptByMapping("Work Schedule","HR Module");
			Collection<ConceptAnswer> workScheduleAnswers;
			if(workSchedule!=null)
				workScheduleAnswers=workSchedule.getAnswers();
			else {
				workScheduleAnswers=new ArrayList<ConceptAnswer>();
			}
			model.addAttribute("workScheduleAnswers",workScheduleAnswers);
		}
		return assignment;
	}
}
