package org.lexusmanson.lexbudget.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.lexusmanson.lexbudget.entity.Transactions;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TransactionValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return Transactions.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "payee", "payee.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reference", "reference.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "category.required");
		
		Transactions temp = (Transactions)obj;
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		String dateString = temp.getDateAsString();
		if(dateString.matches("^\\d{1,2}/\\d{1,2}/\\d{1,4}$")) {
			formatter = DateTimeFormatter.ofPattern("d/M/y");
		}else if(dateString.matches("^\\d{3,4}/\\d{1,2}/\\d{1,2}$")){
			formatter = DateTimeFormatter.ofPattern("y/M/d");
		}
		
		try {
			 temp.setDate(LocalDate.parse(dateString, formatter));
		}catch (Exception e) {
			errors.rejectValue("date", "malformedDate",
					new Object[] {"'date'"}, "The date should be in the format YYYY-MM-DD");
			
		}
		
	}

}
