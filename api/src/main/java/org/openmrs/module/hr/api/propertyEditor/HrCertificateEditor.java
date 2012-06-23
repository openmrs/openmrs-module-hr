package org.openmrs.module.hr.api.propertyEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrCertificate;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.api.HRQualificationService;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class HrCertificateEditor extends PropertyEditorSupport {
    private Log log = LogFactory.getLog(this.getClass());

    public HrCertificateEditor(){

    }
    public void setAsText(String text) throws IllegalArgumentException {
        HRQualificationService hrQualificationService= Context.getService(HRQualificationService.class);
        if (StringUtils.hasText(text)) {
            try {
                setValue(hrQualificationService.getCertificateById(Integer.valueOf(text)));
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
        HrCertificate hrCertificate = (HrCertificate) getValue();
        if (hrCertificate== null) {
            return "";
        } else {
            return hrCertificate.getId().toString();
        }
    }
}
