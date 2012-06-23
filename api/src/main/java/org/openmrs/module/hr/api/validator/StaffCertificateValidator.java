package org.openmrs.module.hr.api.validator;


import org.openmrs.module.hr.HrStaffCert;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StaffCertificateValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return HrStaffCert.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HrStaffCert hrStaffCert = (HrStaffCert)target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hrCertificate", "error.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"currentCertDate","error.null");
        if(hrStaffCert.getCurrentCertDate() != null && hrStaffCert.getCertExpirationDate()!= null)
            if(hrStaffCert.getCurrentCertDate().after(hrStaffCert.getCertExpirationDate()))
                errors.reject("endBeforeStart");
    }
}
