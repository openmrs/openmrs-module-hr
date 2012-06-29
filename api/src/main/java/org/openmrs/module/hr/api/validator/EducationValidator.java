package org.openmrs.module.hr.api.validator;

import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrEducation;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class EducationValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return HrEducation.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        HrEducation education = (HrEducation) o ;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "degree", "error.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "institution", "error.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "institutionLocation", "error.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "major", "error.null");
        if(education.getDegreeYear() != null)
            if(education.getDegreeYear() < 1900 || education.getDegreeYear() > 9999)
                errors.reject("wrongYear");
    }
}
