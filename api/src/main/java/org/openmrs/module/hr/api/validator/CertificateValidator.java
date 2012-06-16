package org.openmrs.module.hr.api.validator;


import org.openmrs.module.hr.HrCertificate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class CertificateValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return HrCertificate.class.equals(aClass);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"certificate","error.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"agency","error.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"levels","error.null");
    }
}
