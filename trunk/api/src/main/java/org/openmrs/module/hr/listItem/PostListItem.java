package org.openmrs.module.hr.listItem;

import org.openmrs.module.hr.HrPost;

public class PostListItem {
	HrPost post;
	String mostRecentIncumbent;
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
