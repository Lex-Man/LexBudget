package org.lexusmanson.lexbudget.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 * NOT USED
 **/

@Aspect
@Component
public class TransactionValidationAspect {

	@Before("execution(* org.lexusmanson.lexbudget.controller.TransactionsController.saveTransaction(..))")
	public void transactionValidator(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		
		for (Object arg: args) {
			System.out.println("There ARGUMENT IS: " + arg);
		}
		
			System.out.println("Aspect called");
		
	}
	
}