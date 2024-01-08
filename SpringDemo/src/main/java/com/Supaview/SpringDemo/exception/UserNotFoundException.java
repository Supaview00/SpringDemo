package com.Supaview.SpringDemo.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(long id) {
		super("The user id : " + id + " doesn't exists");
	}

	public UserNotFoundException(String ex) {
		super(ex);
	}
}
