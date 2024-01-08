package com.Supaview.SpringDemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String StorageExceptionHandler(StorageException storageException) {
		return storageException.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String UserNotFoundExceptionHandler(UserNotFoundException userNotFoundException) {
		return userNotFoundException.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String ProductNotFoundExceptionHandler(ProductNotFoundException productNotFoundException) {
		return productNotFoundException.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String ValidationExceptionHandler(ValidationException validationException) {
		return validationException.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String UserDuplicateExceptionHandler(UserDuplicateException userDuplicateException) {
		return userDuplicateException.getMessage();
	}
}
