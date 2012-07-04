package org.openmrs.module.hr.api.propertyEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrCompetency;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.api.HRCompetencyService;
import org.openmrs.module.hr.api.HRQualificationService;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class HrCompetencyEditor extends PropertyEditorSupport {
    private Log log = LogFactory.getLog(this.getClass());

    public HrCompetencyEditor(){

    }
    public void setAsText(String text) throws IllegalArgumentException {
        HRCompetencyService hrCompetencyService = Context.getService(HRCompetencyService.class);
        if (StringUtils.hasText(text)) {
            try {
                setValue(hrCompetencyService.getCompetencyById(Integer.valueOf(text)));
            }
            catch (Exception ex) {
                log.error("Error setting text" + text, ex);
                throw new IllegalArgumentException("Competency not found: " + ex.getMessage());
            }
        } else {
            setValue(null);
        }
    }

    public String getAsText() {
        HrCompetency hrCompetency = (HrCompetency) getValue();
        if (hrCompetency== null) {
            return "";
        } else {
            return hrCompetency.getId().toString();
        }
    }
}
