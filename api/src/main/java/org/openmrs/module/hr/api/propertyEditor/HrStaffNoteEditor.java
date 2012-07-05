package org.openmrs.module.hr.api.propertyEditor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrCertificate;
import org.openmrs.module.hr.HrStaffNote;
import org.openmrs.module.hr.api.HRNoteService;
import org.openmrs.module.hr.api.HRQualificationService;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class HrStaffNoteEditor extends PropertyEditorSupport {
    private Log log = LogFactory.getLog(this.getClass());

    public HrStaffNoteEditor(){

    }
    public void setAsText(String text) throws IllegalArgumentException {
        HRNoteService hrNoteService= Context.getService(HRNoteService.class);
        if (StringUtils.hasText(text)) {
            try {
                setValue(hrNoteService.getStaffNoteById(Integer.valueOf(text)));
            }
            catch (Exception ex) {
                log.error("Error setting text" + text, ex);
                throw new IllegalArgumentException("staff note not found: " + ex.getMessage());
            }
        } else {
            setValue(null);
        }
    }

    public String getAsText() {
        HrStaffNote hrStaffNote = (HrStaffNote) getValue();
        if (hrStaffNote== null) {
            return "";
        } else {
            return hrStaffNote.getId().toString();
        }
    }
}
