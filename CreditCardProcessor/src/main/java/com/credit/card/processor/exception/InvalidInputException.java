package com.credit.card.processor.exception;

public class InvalidInputException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9169105351704125850L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InvalidInputException(String message) {
		super();
		this.message = message;
	}
	
	

}
