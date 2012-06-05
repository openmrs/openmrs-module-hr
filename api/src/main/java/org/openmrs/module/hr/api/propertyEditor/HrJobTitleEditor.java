package org.openmrs.module.hr.api.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.api.HRPostService;
import org.openmrs.module.hr.api.HRService;
import org.openmrs.module.hr.HrJobTitle;
import org.springframework.util.StringUtils;

public class HrJobTitleEditor extends PropertyEditorSupport{
private Log log = LogFactory.getLog(this.getClass());
	
	public HrJobTitleEditor(){
		
	}
	public void setAsText(String text) throws IllegalArgumentException {
		HRPostService hrPostService=Context.getService(HRPostService.class);
		if (StringUtils.hasText(text)) {
			try {
				setValue(hrPostService.getJobTitleById(Integer.valueOf(text)));
			}
			catch (Exception ex) {
				log.error("Error setting text" + text, ex);
				throw new IllegalArgumentException("job title not found: " + ex.getMessage());
			}
		} else {
			setValue(null);
		}
	}
	
	public String getAsText() {
		HrJobTitle jobTitle = (HrJobTitle) getValue();
		if (jobTitle== null) {
			return "";
		} else {
			return jobTitle.getId().toString();
		}
	}
}
