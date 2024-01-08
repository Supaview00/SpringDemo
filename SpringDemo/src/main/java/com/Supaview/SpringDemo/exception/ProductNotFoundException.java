package com.Supaview.SpringDemo.exception;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(long ex) {
		super("The Product id " + ex + " doesn't exists");
	}
}
