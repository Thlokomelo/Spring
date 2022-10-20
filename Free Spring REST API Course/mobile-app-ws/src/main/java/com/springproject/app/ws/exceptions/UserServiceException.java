package com.springproject.app.ws.exceptions;

//this class handles UserServiceExceptions

public class UserServiceException extends RuntimeException {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3311892875090301273L;

	public UserServiceException(String message)
	{
		super(message);
	}

}
