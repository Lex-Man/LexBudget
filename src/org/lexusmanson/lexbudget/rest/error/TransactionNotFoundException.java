package org.lexusmanson.lexbudget.rest.error;

public class TransactionNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 275882493660295056L;

	public TransactionNotFoundException() {
		super();
	}

	public TransactionNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TransactionNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransactionNotFoundException(String message) {
		super(message);
	}

	public TransactionNotFoundException(Throwable cause) {
		super(cause);
	}
	
	

}
