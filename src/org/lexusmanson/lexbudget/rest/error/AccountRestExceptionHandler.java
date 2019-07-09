package org.lexusmanson.lexbudget.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<LexBudgetErrorResponse> handleException(AccountNotFoundException exc){
	
		// create LexBudgetErrorResponse
		LexBudgetErrorResponse accountNotFound = new LexBudgetErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
		
		// return ResponseEntity
		return new ResponseEntity<>(accountNotFound, HttpStatus.BAD_REQUEST);
		
	}
	
}
