package com.velocity.quiz;

public class InvalidOptionException extends Exception {
	
	private String message;
	
	public InvalidOptionException (String message) {
		super(message);
	}

}
