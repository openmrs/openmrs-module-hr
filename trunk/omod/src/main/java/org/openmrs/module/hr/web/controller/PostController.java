package org.openmrs.module.hr.web.controller;

import java.text.ParseException;
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
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.listItem.PostListItem;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
		List<HrPost> allPosts=hrService.getAllPosts();
		List<PostListItem> postListItemList=new ArrayList<PostListItem>();
		for(HrPost post:allPosts)
		{
			postListItemList.add(new PostListItem(post,hrService.getMostRecentIncumbentForPostbyId(post.getPostId())));		
		}
		model.addAttribute("PostListItemList",postListItemList);
		return SUCCESS_LIST_VIEW;
	}
	@RequestMapping(value="module/hr/admin/post.form")
	@ModelAttribute("post")
	public HrPost showForm(ModelMap model,@RequestParam(value="postId",required=false) Integer postId)
	{
		HRService hrService=Context.getService(HRService.class);
		List<HrJobTitle> jobList= hrService.getAllJobTitles();
		ConceptService cs=Context.getConceptService();
		Concept postStatus=cs.getConceptByMapping("Post Status","HR Module");
		Collection<ConceptAnswer> postStatusAnswers=postStatus.getAnswers();
		LocationService ls=Context.getLocationService();
		List<Location> locationList=ls.getAllLocations();
		model.addAttribute("JobList",jobList);
		model.addAttribute("LocationList", locationList);
		model.addAttribute("PostStatusAnswers", postStatusAnswers);
		HrPost post;
		if(postId!=null)
		post=hrService.getPostById(postId);
		else{
		post=new HrPost();
		}
		return post;
	}
	/**
	 * All the parameters are optional based on the necessity  
	 * 
	 * @param httpSession
	 * @param anyRequestObject
	 * @param errors
	 * @return
	 */
	@RequestMapping(value = "module/hr/admin/post.form",method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request,ModelMap model,@ModelAttribute("post") HrPost post, BindingResult errors) {
		HRService hrService=Context.getService(HRService.class);
		List<HrJobTitle> jobList= hrService.getAllJobTitles();
		ConceptService cs=Context.getConceptService();
		Concept postStatus=cs.getConceptByMapping("Post Status","HR Module");
		Collection<ConceptAnswer> postStatusAnswers=postStatus.getAnswers();
		LocationService ls=Context.getLocationService();
		List<Location> locationList=ls.getAllLocations();
		model.addAttribute("JobList",jobList);
		model.addAttribute("LocationList", locationList);
		model.addAttribute("PostStatusAnswers", postStatusAnswers);
		if (Context.isAuthenticated()) {
			if (request.getParameter("retirePost") != null) {
				String retireReason = request.getParameter("retireReason");
				if (post.getId() != null && (retireReason == null || retireReason.length() == 0)) {
					errors.reject("retireReason", "Retire reason cannot be empty");
					return SUCCESS_FORM_VIEW;
				}
				hrService.retirePost(hrService.getPostById(post.getId()), retireReason);
				request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Post Retired Successfully");
				return showList(model);
			}
			else if (request.getParameter("unretirePost") != null) {
				hrService.unretirePost(hrService.getPostById(post.getId()));
				request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Post Unretired Successfully");
				return showList(model);
			} else {
				if (errors.hasErrors()) {
					return SUCCESS_FORM_VIEW;
				}
				else{
				post.setHrJobTitle(hrService.getJobTitleById(Integer.parseInt(request.getParameter("job"))));
				post.setLocation(ls.getLocation(Integer.parseInt(request.getParameter("location"))));
				hrService.savePost(post);
				request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Post saved Successfully");
				return showList(model);
				}
			}
		}
		return SUCCESS_FORM_VIEW;
	}
}
