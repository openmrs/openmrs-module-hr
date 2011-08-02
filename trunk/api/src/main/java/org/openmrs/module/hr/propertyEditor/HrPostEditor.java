package org.openmrs.module.hr.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrPost;
import org.springframework.util.StringUtils;

public class HrPostEditor extends PropertyEditorSupport{
private Log log = LogFactory.getLog(this.getClass());
	
	public HrPostEditor(){
		
	}
	public void setAsText(String text) throws IllegalArgumentException {
		HRService hrService=Context.getService(HRService.class);
		if (StringUtils.hasText(text)) {
			try {
				setValue(hrService.getPostById(Integer.valueOf(text)));
			}
			catch (Exception ex) {
				log.error("Error setting text" + text, ex);
				throw new IllegalArgumentException("Concept not found: " + ex.getMessage());
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
