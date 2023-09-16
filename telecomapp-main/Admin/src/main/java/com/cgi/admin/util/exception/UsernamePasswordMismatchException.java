package com.cgi.admin.util.exception;

public class UsernamePasswordMismatchException extends Exception{

	private static final long serialVersionUID = 1L;

	public UsernamePasswordMismatchException(String message) {
        super(message);
	}
}
