package org.openmrs.module.hr.api.validator;

import org.openmrs.module.hr.HrTrainingClass;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TrainingClassValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return HrTrainingClass.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        //To change body of implemented methods use File | Settings | File Templates.
    }
}
