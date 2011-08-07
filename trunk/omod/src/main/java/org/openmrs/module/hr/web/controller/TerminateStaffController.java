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
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRManagerService;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrAssignment;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.HrPostHistory;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.propertyEditor.HrPostEditor;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class TerminateStaffController {
	/** Logger for this class and subclasses */
	protected static final Log log = LogFactory.getLog(StaffController.class);
	
	private final String SUCCESS_FORM_VIEW = "/module/hr/manager/terminateStaff";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		NumberFormat nf = NumberFormat.getInstance(Context.getLocale());
		binder.registerCustomEditor(java.lang.Integer.class, new CustomNumberEditor(java.lang.Integer.class, nf, true));
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(Context.getDateFormat(), true, 10));
		binder.registerCustomEditor(org.openmrs.Concept.class, new ConceptEditor());
	}
	
	@RequestMapping(value = "module/hr/manager/terminateStaff.form",method = RequestMethod.GET)
	@ModelAttribute("postHistory")
	public HrPostHistory showForm(ModelMap model){
		ConceptService cs=Context.getConceptService();
		Concept endReason=cs.getConceptByMapping("Post history end reason","HR Module");
		Collection<ConceptAnswer> postHistoryEndReasons;
		if(endReason!=null)
			postHistoryEndReasons=endReason.getAnswers();
		else {
			postHistoryEndReasons=new ArrayList<ConceptAnswer>();
		}
		model.addAttribute("EndReasons",postHistoryEndReasons);
		return new HrPostHistory();
		
	}
	@RequestMapping(value = "module/hr/manager/terminateStaff.form",method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request,ModelMap model,@ModelAttribute("postHistory") HrPostHistory postHistory,BindingResult errors) {
		if(request.getParameter("submit").equals("Cancel"))
		{
			return "redirect:/module/hr/manager/staffPosition.list";
		}
		else{
			HrStaff staff=(HrStaff)request.getSession().getAttribute("staff");
			HRManagerService hrManagerService= Context.getService(HRManagerService.class);
			HrPostHistory postHistoryInstance=hrManagerService.getCurrentPostForStaff(staff.getStaffId());
			ValidationUtils.rejectIfEmpty(errors,"endDate","error.null");
			ValidationUtils.rejectIfEmpty(errors,"endReason","error.null");
			if(postHistory.getEndReason()!=null){
			if(postHistory.getEndReason().getName().getName().endsWith(":"))
				if(postHistory.getEndReasonOther()==null)
					ValidationUtils.rejectIfEmpty(errors,"endReasonOther","error.null");
			}
			if(postHistoryInstance.getStartDate()!=null && postHistory.getEndDate()!=null){
			if(postHistoryInstance.getStartDate().after(postHistory.getEndDate()))
			errors.reject("startBeforeEnd","End Date cannot be before start date");
			}
			if(errors.hasErrors()){
				ConceptService cs=Context.getConceptService();
				Concept endReason=cs.getConceptByMapping("Post history end reason","HR Module");
				Collection<ConceptAnswer> postHistoryEndReasons;
				if(endReason!=null)
					postHistoryEndReasons=endReason.getAnswers();
				else {
					postHistoryEndReasons=new ArrayList<ConceptAnswer>();
				}
				model.addAttribute("EndReasons",postHistoryEndReasons);
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
			HrPost post=Context.getService(HRService.class).getPostById(postHistoryInstance.getHrPost().getPostId());
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
			Context.getService(HRService.class).savePost(post);
			Concept staffStatusQuestion=cs.getConceptByMapping("Staff status","HR Module");
			Concept former=null;
			if(staffStatusQuestion!=null){
				Collection<ConceptAnswer> answers=staffStatusQuestion.getAnswers();
				Iterator<ConceptAnswer> ansiter=answers.iterator();
				while(ansiter.hasNext())
				{
					if((former=ansiter.next().getAnswerConcept()).getName().getName().equals("Former"));
					break;
				}
			}
			request.getSession().removeAttribute("staff");
			staff.setStaffStatus(former);
			Context.getService(HRService.class).saveStaff(staff);
			request.getSession().setAttribute("staff",staff);
		}

		request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Terminated successfully");
		return "redirect:/module/hr/manager/staffPosition.list?terminated=true";

	}
	
}
