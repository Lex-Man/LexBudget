package org.lexusmanson.lexbudget.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AccountsExceptionHandler {

	@ExceptionHandler(NameMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleException(NameMismatchException exc, Model model){
		
		
		return "error/badRequest";	
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleException(Exception exc){
		
		
		return "error/badRequest";
	}
	
	
}
