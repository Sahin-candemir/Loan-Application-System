package com.definex.loanapplication.exception;

public class IdentityNoOrPhoneNumberAllreadyExists extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IdentityNoOrPhoneNumberAllreadyExists(String message) {
		super(message);
	}
}
