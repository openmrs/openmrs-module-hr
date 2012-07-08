package org.openmrs.module.hr.api.validator;

import org.openmrs.module.hr.HrTraining;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class TrainingValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return HrTraining.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"category","error.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","error.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description","error.null");
    }
}
