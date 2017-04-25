package com.swk.exception;

public class RepeatAppointException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public RepeatAppointException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RepeatAppointException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
