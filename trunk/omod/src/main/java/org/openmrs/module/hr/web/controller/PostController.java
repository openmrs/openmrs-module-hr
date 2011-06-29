package org.openmrs.module.hr.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.LocationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrPost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class PostController {
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	/** Success form view name */
	private final String SUCCESS_LIST_VIEW = "/module/hr/admin/posts";
	private final String SUCCESS_FORM_VIEW = "/module/hr/admin/post";
	
	/**
	 * Initially called after the formBackingObject method to get the landing form name  
	 * @return String form view name
	 */
	@RequestMapping(value = "module/hr/admin/posts.list",method = RequestMethod.GET)
	public String showList(ModelMap model){
		HRService hrService=Context.getService(HRService.class);
		List<HrPost> postList= hrService.getAllPosts();
		model.addAttribute("PostList",postList);
		HrPost hrpost=new HrPost();
		hrpost.setHrJobTitle(hrService.getAllJobTitles().get(0));
		hrpost.setLocation(Context.getLocationService().getAllLocations().get(1));
		hrService.savePost(hrpost);
		return SUCCESS_LIST_VIEW;
	}
	
	/**
	 * All the parameters are optional based on the necessity  
	 * 
	 * @param httpSession
	 * @param anyRequestObject
	 * @param errors
	 * @return
	 */
	@RequestMapping(value = "module/hr/admin/posts.form",method = RequestMethod.POST)
	public String onSubmit(HttpSession httpSession,
	                               @ModelAttribute("anyRequestObject") Object anyRequestObject, BindingResult errors) {
		
		if (errors.hasErrors()) {
			return showForm(errors);
		}
		
		return SUCCESS_FORM_VIEW;
	}
	@RequestMapping(value="module/hr/admin/post.form",method=RequestMethod.GET)
	public String showForm(Errors errors)
	{
		return SUCCESS_FORM_VIEW;
	}
}
