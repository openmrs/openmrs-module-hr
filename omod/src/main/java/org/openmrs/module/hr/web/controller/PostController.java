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
import org.openmrs.api.LocationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.api.HRPostService;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.api.listItem.PostListItem;
import org.openmrs.module.hr.api.propertyEditor.HrJobTitleEditor;
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

@Controller

public class PostController {
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	/** Success form view name */
	private final String SUCCESS_LIST_VIEW = "/module/hr/admin/posts";
	private final String SUCCESS_FORM_VIEW = "/module/hr/admin/post";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		NumberFormat nf = NumberFormat.getInstance(Context.getLocale());
		binder.registerCustomEditor(java.lang.Integer.class, new CustomNumberEditor(java.lang.Integer.class, nf, true));
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(Context.getDateFormat(), true, 10));
		binder.registerCustomEditor(org.openmrs.Concept.class, new ConceptEditor());
		binder.registerCustomEditor(Location.class, new LocationEditor());
		binder.registerCustomEditor(HrJobTitle.class,new HrJobTitleEditor());
	}
	/**
	 * Initially called after the formBackingObject method to get the landing form name  
	 * @return String form view name
	 */
	@RequestMapping(value = "module/hr/admin/posts.list",method = RequestMethod.GET)
	public String showList(ModelMap model,@RequestParam(required=false,value="allposts") boolean allpostsIncluded,@RequestParam(required=false,value="alllocations") boolean allLocationsIncluded){
		HRPostService hrPostService=Context.getService(HRPostService.class);
		List<HrPost> allPosts=hrPostService.getAllPosts(allpostsIncluded,allLocationsIncluded);
		List<PostListItem> postListItemList=new ArrayList<PostListItem>();
		for(HrPost post:allPosts)
		{
			postListItemList.add(new PostListItem(post,hrPostService.getMostRecentIncumbentForPostbyId(post.getPostId())));
		}
		model.addAttribute("PostListItemList",postListItemList);
		return SUCCESS_LIST_VIEW;
	}
	@RequestMapping(value="module/hr/admin/post.form")
	@ModelAttribute("post")
	public HrPost showForm(ModelMap model,@RequestParam(value="postId",required=false) Integer postId)
	{
		HRPostService hrPostService=Context.getService(HRPostService.class);
		List<HrJobTitle> jobList= hrPostService.getAllJobTitles();
		ConceptService cs=Context.getConceptService();
		Concept postStatus=cs.getConceptByMapping("Post Status","HR Module");
		Collection<ConceptAnswer> postStatusAnswers;
		if(postStatus!=null)
		postStatusAnswers=postStatus.getAnswers();
		else {
			postStatusAnswers=new ArrayList<ConceptAnswer>();
		}
		LocationService ls=Context.getLocationService();
		List<Location> locationList=ls.getAllLocations();
		model.addAttribute("JobList",jobList);
		model.addAttribute("LocationList", locationList);
		model.addAttribute("PostStatusAnswers", postStatusAnswers);
		HrPost post;
		if(postId!=null){
		post=hrPostService.getPostById(postId);
		if(post==null)
			post=new HrPost();
		}
		else{
		post=new HrPost();
		}
		return post;
	}
	@ModelAttribute("prevStatus")
	public Concept getStatus(@RequestParam(value="postId",required=false) Integer postId){
		HRPostService hrPostService=Context.getService(HRPostService.class);
		if(postId!=null)
		{
			HrPost post=hrPostService.getPostById(postId);
			return post!=null?post.getStatus():null;
		}
		else
			return null;
		
	}

	@RequestMapping(value = "module/hr/admin/post.form",method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request,ModelMap model,@ModelAttribute("post") HrPost post, BindingResult errors,@ModelAttribute("prevStatus") Concept prevStatus) {
		HRPostService hrPostService=Context.getService(HRPostService.class);
		List<HrJobTitle> jobList= hrPostService.getAllJobTitles();
		ConceptService cs=Context.getConceptService();
		Concept postStatus=cs.getConceptByMapping("Post Status","HR Module");
		Collection<ConceptAnswer> postStatusAnswers;
		if(postStatus!=null)
		postStatusAnswers=postStatus.getAnswers();
		else {
			postStatusAnswers=new ArrayList<ConceptAnswer>();
		}
		LocationService ls=Context.getLocationService();
		List<Location> locationList=ls.getAllLocations();
		model.addAttribute("JobList",jobList);
		model.addAttribute("LocationList", locationList);
		model.addAttribute("PostStatusAnswers", postStatusAnswers);
		if (Context.isAuthenticated()) {
			ValidationUtils.rejectIfEmpty(errors, "hrJobTitle","error.null");
			ValidationUtils.rejectIfEmpty(errors, "location","error.null");
			if (request.getParameter("retirePost") != null) {
				String retireReason = request.getParameter("retireReason");
				if (post.getId() != null && (retireReason == null || retireReason.length() == 0)) {
					errors.reject("retireReason", "Retire reason cannot be empty");
					return SUCCESS_FORM_VIEW;
				}
				hrPostService.retirePost(hrPostService.getPostById(post.getId()), retireReason);
				request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Post Retired Successfully");
				return showList(model,false,false);
			}
			else if (request.getParameter("unretirePost") != null) {
				hrPostService.unretirePost(hrPostService.getPostById(post.getId()));
				request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Post Unretired Successfully");
				return showList(model,false,false);
			} else {
				if(prevStatus!=null)
				{
					if(prevStatus.getName().getName().equals("Filled"))
						if(!prevStatus.getId().equals(post.getStatus().getId()))
							errors.reject("statusChange","Cannot change status to the specified one.");
					if(prevStatus.getName().getName().equals("Open"))
						if(post.getStatus().getName().getName().equals("Filled"))
							errors.reject("statusChange","Cannot change status to the specified one.");
				}
				if (errors.hasErrors()) {
					return SUCCESS_FORM_VIEW;
				}
				else{	
				hrPostService.savePost(post);
				request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Post saved Successfully");
				return showList(model,false,false);
				}
			}
		}
		return SUCCESS_FORM_VIEW;
	}
}
