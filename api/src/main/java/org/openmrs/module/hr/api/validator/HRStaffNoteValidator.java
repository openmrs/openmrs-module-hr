package org.openmrs.module.hr.api.validator;

import org.openmrs.module.hr.HrStaffNote;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class HRStaffNoteValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(HrStaffNote.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
