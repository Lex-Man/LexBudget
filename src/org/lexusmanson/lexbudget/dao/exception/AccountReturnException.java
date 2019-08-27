package org.lexusmanson.lexbudget.dao.exception;

public class AccountReturnException extends RuntimeException{

	public AccountReturnException() {
		super();
	}

	public AccountReturnException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public AccountReturnException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AccountReturnException(String arg0) {
		super(arg0);
	}

	public AccountReturnException(Throwable arg0) {
		super(arg0);
	}
	
}
