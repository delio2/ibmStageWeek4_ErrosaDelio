package com.stage.cic.exception;

public class BadRequestException extends Exception {

	private static final long serialVersionUID = 4518915303814552167L;
	
	public BadRequestException(String message) {
		super(message);
	}

}
