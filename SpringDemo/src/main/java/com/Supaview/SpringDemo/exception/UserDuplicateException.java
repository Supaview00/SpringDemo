package com.Supaview.SpringDemo.exception;

public class UserDuplicateException extends RuntimeException {

	public UserDuplicateException(String ex) {
		super("the username already exists");
	}
}
