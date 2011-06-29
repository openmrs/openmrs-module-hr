/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.hr.web.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrIscoCodes;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrPost;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * This class configured as controller using annotation and mapped with the URL of 'module/hr/humanresourcemoduleLink.form'.
 */
@Controller
public class JobTitleController{
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	/** Success list view name */
	private final String SUCCESS_LIST_VIEW = "/module/hr/admin/jobs";
	/** Success form view name */
	private final String SUCCESS_FORM_VIEW = "/module/hr/admin/job";

	@RequestMapping(value = "module/hr/admin/jobs.list")
	public String showList(ModelMap model){
		HRService hrService=Context.getService(HRService.class);
		List<HrJobTitle> jobList= hrService.getAllJobTitles();
		model.addAttribute("JobList",jobList);
		return SUCCESS_LIST_VIEW;
	}
	

	@RequestMapping(value="module/hr/admin/job.form")
	@ModelAttribute("job")
	public HrJobTitle showForm(ModelMap model,@RequestParam(value="jobId",required=false) Integer jobId,@ModelAttribute(value="job") HrJobTitle jobTitle,Errors errors)
	{
		HRService hrService=Context.getService(HRService.class);
		List<HrIscoCodes> iscoCodeList= hrService.getAllIscoCodes();
		ConceptService cs=Context.getConceptService();
		Concept cadre=cs.getConceptByMapping("Cadre","HR Module");
		Collection<ConceptAnswer> cadreAnswers=cadre.getAnswers();
		model.addAttribute("IscoCodeList",iscoCodeList);
		model.addAttribute("CadreAnswers", cadreAnswers);
		if(jobId!=null)
		jobTitle=hrService.getJobTitleById(jobId);
		else{
		jobTitle=new HrJobTitle();
		}
		return jobTitle;
	}
	/**
	 * All the parameters are optional based on the necessity  
	 * 
	 * @param httpSession
	 * @param anyRequestObject
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="module/hr/admin/job.form",method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request,ModelMap model,@ModelAttribute("job") HrJobTitle jobTitle, BindingResult errors) {
		
		if (Context.isAuthenticated()) {
			
			HRService hrService=Context.getService(HRService.class);
			List<HrIscoCodes> iscoCodeList= hrService.getAllIscoCodes();
			//ConceptService cs=Context.getConceptService();
			//Concept cadre=cs.getConceptByMapping("Cadre","HR Module");
			//Collection<ConceptAnswer> cadreAnswers=cadre.getAnswers();
			model.addAttribute("IscoCodeList",iscoCodeList);
			//model.addAttribute("CadreAnswers", cadreAnswers);
			if (request.getParameter("retireJobTitle") != null) {
				String retireReason = request.getParameter("retireReason");
				if (jobTitle.getId() != null && (retireReason == null || retireReason.length() == 0)) {
					errors.reject("retireReason", "Retire reason cannot be empty");
					return SUCCESS_FORM_VIEW;
				}
				hrService.retireJobTitle(jobTitle, retireReason);
				request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Job Title Retired Successfully");
				return showList(model);
			}
			else if (request.getParameter("unretireJobTitle") != null) {
				hrService.unretireJobTitle(jobTitle);
				request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Job Title Unretired Successfully");
				return showList(model);
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,"title", "error.null");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,"cadre", "error.null");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,"grades", "error.null");
				if(request.getParameter("IscoCode")==null)				
				errors.reject("IscoCode", "error.null");
				if (errors.hasErrors()) {
					return SUCCESS_FORM_VIEW;
				}
				else{
				jobTitle.setHrIscoCodes(hrService.getIscoCodeById(request.getParameter("IscoCode")));
				hrService.saveJobTitle(jobTitle);
				request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Job Title saved Successfully");
				return showList(model);
				}
			}
		}
		return SUCCESS_FORM_VIEW;
	}
	
	/**
	 * This class returns the form backing object. This can be a string, a boolean, or a normal java
	 * pojo. The bean name defined in the ModelAttribute annotation and the type can be just
	 * defined by the return type of this method
	 */
}
