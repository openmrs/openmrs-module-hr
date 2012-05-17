package org.openmrs.module.hr.validator;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;
import org.openmrs.validator.PersonNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {
private static Log log = LogFactory.getLog(PersonValidator.class);
	
	@Autowired
	PersonNameValidator personNameValidator=new PersonNameValidator();
	
	@Autowired
	PersonAddressValidator personAddressValidator=new PersonAddressValidator();
	
	public boolean supports(Class<?> clazz) {
		if (log.isDebugEnabled())
			log.debug(this.getClass().getName() + ".supports: " + clazz.getName());
		return Person.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		if (log.isDebugEnabled())
			log.debug(this.getClass().getName() + ".validate...");
		
		Person person = (Person) obj;
		
		if (person != null) {
			for (PersonName personName : person.getNames()) {
				personNameValidator.validate(personName, errors);
			}
			
			//validate the personAddress
			int index = 0;
			for (PersonAddress address : person.getAddresses()) {
				try {
					errors.pushNestedPath("addresses[" + index + "]");
					ValidationUtils.invokeValidator(personAddressValidator, address, errors);
				}
				finally {
					errors.popNestedPath();
					index++;
				}
			}
		}
		// Make sure they choose a gender
		if (StringUtils.isBlank(person.getGender()))
			errors.rejectValue("gender", "Person.gender.required");
		
		// check person birthdate against future dates and really old dates
		if (person.getBirthdate() != null) {
			if (person.getBirthdate().after(new Date()))
				errors.rejectValue("birthdate", "error.date.future");
			else {
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				c.add(Calendar.YEAR, -120); // person cannot be older than 120 years old 
				if (person.getBirthdate().before(c.getTime())) {
					errors.rejectValue("birthdate", "error.date.nonsensical");
				}
			}
		}
		
		//	 person Info 
		if (person.isVoided())
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "voidReason", "error.null");
		if (person.isDead() && (person.getCauseOfDeath() == null))
			errors.rejectValue("causeOfDeath", "cause of death null");
		
		
	}

	}


