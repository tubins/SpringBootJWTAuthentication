package com.tubz.exception;

public class MyRuntimeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyRuntimeException() {
		super();
	}

	public MyRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MyRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyRuntimeException(String message) {
		super(message);
	}

	public MyRuntimeException(Throwable cause) {
		super(cause);
	}

}
