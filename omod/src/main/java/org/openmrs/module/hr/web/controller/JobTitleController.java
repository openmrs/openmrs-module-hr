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


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Location;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.api.HRPostService;
import org.openmrs.module.hr.HrIscoCodes;
import org.openmrs.module.hr.HrJobTitle;
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
import org.springframework.web.servlet.ModelAndView;


/**
 * This class configured as controller using annotation and mapped with the URL of 'module/hr/humanresourcemoduleLink.form'.
 */
@Controller
public class JobTitleController{
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	/** Success list view name */
	private final String SUCCESS_LIST_VIEW = "/module/hr/admin/jobTitles";
	/** Success form view name */
	private final String SUCCESS_FORM_VIEW = "/module/hr/admin/jobTitle";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		NumberFormat nf = NumberFormat.getInstance(Context.getLocale());
		binder.registerCustomEditor(java.lang.Integer.class, new CustomNumberEditor(java.lang.Integer.class, nf, true));
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(Context.getDateFormat(), true, 10));
		binder.registerCustomEditor(org.openmrs.Concept.class, new ConceptEditor());
		binder.registerCustomEditor(Location.class, new LocationEditor());
	}

	@RequestMapping(value = "module/hr/admin/jobTitles.list")
	public String showList(ModelMap model){
		HRPostService hrPostService=Context.getService(HRPostService.class);
		List<HrJobTitle> jobList= hrPostService.getAllJobTitles();
		model.addAttribute("JobList",jobList);
		return SUCCESS_LIST_VIEW;
	}
	
	@ModelAttribute("job")
	@RequestMapping(value="module/hr/admin/jobTitle.form")
	public HrJobTitle showForm(ModelMap model,@RequestParam(value="jobId",required=false) Integer jobId)
	{
		HRPostService hrPostService=Context.getService(HRPostService.class);
		List<HrIscoCodes> iscoCodeList= hrPostService.getAllIscoCodes();
		ConceptService cs=Context.getConceptService();
		Concept cadre=cs.getConceptByMapping("Cadre","HR Module");
		Collection<ConceptAnswer> cadreAnswers;
		if(cadre!=null)
			cadreAnswers=cadre.getAnswers();
		else
			cadreAnswers=new ArrayList<ConceptAnswer>();
		model.addAttribute("IscoCodeList",iscoCodeList);
		model.addAttribute("CadreAnswers", cadreAnswers);
		HrJobTitle jobTitle;
		if(jobId!=null){
		jobTitle=hrPostService.getJobTitleById(jobId);
		if(jobTitle==null)
			jobTitle=new HrJobTitle();
		}
		else{
		jobTitle=new HrJobTitle();
		}
		return jobTitle;
	}

	@RequestMapping(value="module/hr/admin/jobTitle.form",method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpServletRequest request,@ModelAttribute("job") HrJobTitle jobTitle, BindingResult errors) {
		HRPostService hrPostService=Context.getService(HRPostService.class);
		List<HrIscoCodes> iscoCodeList= hrPostService.getAllIscoCodes();
		ConceptService cs=Context.getConceptService();
		Concept cadre=cs.getConceptByMapping("Cadre","HR Module");
		Collection<ConceptAnswer> cadreAnswers;
		if(cadre!=null)
			cadreAnswers=cadre.getAnswers();
		else
			cadreAnswers=new ArrayList<ConceptAnswer>();
		ModelAndView formView=new ModelAndView(SUCCESS_FORM_VIEW);
		formView.addObject("IscoCodeList", iscoCodeList);
		formView.addObject("CadreAnswers", cadreAnswers);
		List<HrJobTitle> jobList=null;
		if (Context.isAuthenticated()) {
			ModelAndView listView=new ModelAndView(SUCCESS_LIST_VIEW);
			if (request.getParameter("retireJobTitle") != null) {
				String retireReason = request.getParameter("retireReason");
				if (jobTitle.getId() != null && (retireReason == null || retireReason.length() == 0)) {
					errors.reject("retireReason", "Retire reason cannot be empty");
					return formView;
				}
				hrPostService.retireJobTitle(hrPostService.getJobTitleById(jobTitle.getId()), retireReason,Context.getAuthenticatedUser());
				request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Job Title Retired Successfully");
				jobList=hrPostService.getAllJobTitles();
				listView.addObject("JobList", jobList);
				return listView;
			}
			else if (request.getParameter("unretireJobTitle") != null) {
				hrPostService.unretireJobTitle(hrPostService.getJobTitleById(jobTitle.getId()));
				request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Job Title Unretired Successfully");
				jobList=hrPostService.getAllJobTitles();
				listView.addObject("JobList", jobList);
				return listView;
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,"title", "error.null");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,"cadre", "error.null");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,"grades", "error.null");
				if(request.getParameter("IscoCode")==null)				
				errors.reject("IscoCode", "error.null");
				if (errors.hasErrors()) {
					return formView;
				}
				else{
				jobTitle.setHrIscoCodes(hrPostService.getIscoCodeById(request.getParameter("IscoCode")));
				hrPostService.saveJobTitle(jobTitle);
				request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Job Title saved Successfully");
				jobList=hrPostService.getAllJobTitles();
				listView.addObject("JobList", jobList);
				return listView;
				}
			}
		}
		return formView;
	}
	
	/**
	 * This class returns the form backing object. This can be a string, a boolean, or a normal java
	 * pojo. The bean name defined in the ModelAttribute annotation and the type can be just
	 * defined by the return type of this method
	 */
}
