package org.openmrs.module.hr.api.propertyEditor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.api.HRQualificationService;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

public class HrTrainingEditor extends PropertyEditorSupport {
    private Log log = LogFactory.getLog(this.getClass());

    public HrTrainingEditor(){

    }
    public void setAsText(String text) throws IllegalArgumentException {
        HRQualificationService hrQualificationService= Context.getService(HRQualificationService.class);
        if (StringUtils.hasText(text)) {
            try {
                setValue(hrQualificationService.getCertificateById(Integer.valueOf(text)));
            }
            catch (Exception ex) {
                log.error("Error setting text" + text, ex);
                throw new IllegalArgumentException("training not found: " + ex.getMessage());
            }
        } else {
            setValue(null);
        }
    }

    public String getAsText() {
        HrTraining hrTraining = (HrTraining) getValue();
        if (hrTraining== null) {
            return "";
        } else {
            return hrTraining.getId().toString();
        }
    }
}
