package org.lexusmanson.lexbudget.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransactionRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<LexBudgetErrorResponse> handleException(TransactionNotFoundException exc){
		
		// create CustomerErrorResponse
		LexBudgetErrorResponse error = new LexBudgetErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
		
		// return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	

}
