package org.lexusmanson.lexbudget.validator;

import org.lexusmanson.lexbudget.entity.Accounts;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AccountsValidator implements Validator {

	@Override
	public boolean supports(Class<?> obj) {
		return Accounts.class.equals(obj);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "organisation", "organisation.requried");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "function", "function.required");
	}

}
