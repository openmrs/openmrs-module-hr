package org.openmrs.module.hr.api.propertyEditor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrStaffNote;
import org.openmrs.module.hr.HrTrainingClass;
import org.openmrs.module.hr.api.HRNoteService;
import org.openmrs.module.hr.api.HRTrainingService;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class HrTrainingClassEditor  extends PropertyEditorSupport{
    private Log log = LogFactory.getLog(this.getClass());

    public HrTrainingClassEditor(){

    }
    public void setAsText(String text) throws IllegalArgumentException {
        HRTrainingService hrTrainingService= Context.getService(HRTrainingService.class);
        if (StringUtils.hasText(text)) {
            try {
                setValue(hrTrainingService.getTrainingClassById(Integer.valueOf(text)));
            }
            catch (Exception ex) {
                log.error("Error setting text" + text, ex);
                throw new IllegalArgumentException("training class not found: " + ex.getMessage());
            }
        } else {
            setValue(null);
        }
    }

    public String getAsText() {
        HrTrainingClass hrTrainingClass = (HrTrainingClass) getValue();
        if (hrTrainingClass== null) {
            return "";
        } else {
            return hrTrainingClass.getId().toString();
        }
    }
}
