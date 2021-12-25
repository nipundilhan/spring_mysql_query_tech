package com.sprng.comn_comman.core;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sprng.comn_comman.exception.ApplicationException;
import com.sprng.comn_comman.exception.ValidationListException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class RootExceptionHandler {

	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<?> handleApplicationException(ApplicationException ex) {
	    return new ResponseEntity<>(ex.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(ValidationListException.class)
	public ResponseEntity<?> handleValidationListException(ValidationListException ex) {
	    return new ResponseEntity<>(ex.getValidationList(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex) {
	    return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
