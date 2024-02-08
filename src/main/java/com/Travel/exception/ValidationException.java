package com.Travel.exception;

public class ValidationException extends GlobalException{
	
	
	private String errorMessage;

	public ValidationException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	@Override
	public String getMessage() {
		return this.errorMessage;
	}

}
