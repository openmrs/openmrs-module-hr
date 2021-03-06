package org.openmrs.module.hr.api.listItem;

import org.openmrs.module.hr.HrPost;

public class PostListItem {
	private HrPost post;
	private String mostRecentIncumbent;
	public PostListItem() {
		
	}
	public PostListItem(HrPost post, String mostRecentIncumbent) {
		super();
		this.post = post;
		this.mostRecentIncumbent = mostRecentIncumbent;
	}
	public HrPost getPost() {
		return post;
	}
	
	public void setPost(HrPost post) {
		this.post = post;
	}
	public String getMostRecentIncumbent() {
		return mostRecentIncumbent;
	}
	public void setMostRecentIncumbent(String mostRecentIncumbent) {
		this.mostRecentIncumbent = mostRecentIncumbent;
	}
	
}
