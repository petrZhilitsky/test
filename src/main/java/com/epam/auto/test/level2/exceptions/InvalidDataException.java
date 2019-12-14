package com.epam.auto.test.level2.exceptions;

public class InvalidDataException extends Exception{
	private static final long serialVersionUID = 1L;
	private String result;
	
	public InvalidDataException(String message, String result) {
		super(message);
		this.result = result;
	}
	
	public InvalidDataException(String message) {
		super(message);
	}

	public String getResult() {
		return result;
	}
}
