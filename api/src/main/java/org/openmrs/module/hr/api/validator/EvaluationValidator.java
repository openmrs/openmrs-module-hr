package org.openmrs.module.hr.api.validator;

import org.openmrs.module.hr.HrEvaluation;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EvaluationValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return HrEvaluation.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hrCompetency", "error.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"level","error.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"evaluationDate","error.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"evaluator","error.null");
    }
}
