package org.openmrs.module.hr.api.validator;

import org.openmrs.module.hr.HrCertificate;
import org.openmrs.module.hr.HrCompetency;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CompetencyValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return HrCompetency.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"levels","error.null");
        HrCompetency competency = (HrCompetency)o;
        if(competency.getLevels().endsWith(","))
            errors.reject("endsWithComma");
        if(competency.getLevels().startsWith(","))
            errors.reject("startsWithComma");
    }
}
