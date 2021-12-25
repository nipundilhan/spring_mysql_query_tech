package com.sprng.comn_comman.exception;

import java.util.List;

import com.sprng.comn_comman.core.resource.response.error.ValidateResponse;

public class ValidationListException extends Exception {
	
	List<ValidateResponse> validationList;

	public ValidationListException() {
		super();
	}
	
	public ValidationListException(List<ValidateResponse> validationList) {
		super();
		this.validationList = validationList;
	}
	
	
	
	public List<ValidateResponse> getValidationList() {
		return validationList;
	}

	public void setValidationList(List<ValidateResponse> validationList) {
		this.validationList = validationList;
	}

	
	

}
