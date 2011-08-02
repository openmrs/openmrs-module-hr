package org.openmrs.module.hr.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRManagerService;
import org.openmrs.module.hr.HrPostHistory;
import org.springframework.util.StringUtils;

public class HrPostHistoryEditor extends PropertyEditorSupport{
	private Log log = LogFactory.getLog(this.getClass());
	
	public HrPostHistoryEditor(){
		
	}
	public void setAsText(String text) throws IllegalArgumentException {
		HRManagerService hrManagerService=Context.getService(HRManagerService.class);
		if (StringUtils.hasText(text)) {
			try {
				setValue(hrManagerService.getPostHistoryById(Integer.valueOf(text)));
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
		HrPostHistory postHistory = (HrPostHistory) getValue();
		if (postHistory == null) {
			return "";
		} else {
			return postHistory.getId().toString();
		}
	}
}
