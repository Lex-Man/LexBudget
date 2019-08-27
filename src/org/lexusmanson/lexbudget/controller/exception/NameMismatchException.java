package org.lexusmanson.lexbudget.controller.exception;

public class NameMismatchException extends RuntimeException{
	
	public NameMismatchException() {
		super();
	}

	public NameMismatchException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public NameMismatchException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NameMismatchException(String arg0) {
		super(arg0);
	}

	public NameMismatchException(Throwable arg0) {
		super(arg0);
	}

}
