package org.openmrs.module.hr.api.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.api.HRPostService;
import org.openmrs.module.hr.api.HRService;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.api.db.HRPostDAO;
import org.springframework.util.StringUtils;

public class HrPostEditor extends PropertyEditorSupport{
private Log log = LogFactory.getLog(this.getClass());
	
	public HrPostEditor(){
		
	}
	public void setAsText(String text) throws IllegalArgumentException {
        HRPostService hrPostService=Context.getService(HRPostService.class);
		if (StringUtils.hasText(text)) {
			try {
				setValue(hrPostService.getPostById(Integer.valueOf(text)));
			}
			catch (Exception ex) {
				log.error("Error setting text" + text, ex);
				throw new IllegalArgumentException("post not found: " + ex.getMessage());
			}
		} else {
			setValue(null);
		}
	}
	
	public String getAsText() {
		HrPost post = (HrPost) getValue();
		if (post== null) {
			return "";
		} else {
			return post.getId().toString();
		}
	}
}
