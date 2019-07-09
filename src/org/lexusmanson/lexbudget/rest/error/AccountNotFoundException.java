package org.lexusmanson.lexbudget.rest.error;

public class AccountNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1695359044021185328L;

	public AccountNotFoundException() {
		super();
	}

	public AccountNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	public AccountNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public AccountNotFoundException(String arg0) {
		super(arg0);
		
	}

	public AccountNotFoundException(Throwable arg0) {
		super(arg0);
		
	}

	
	
}
