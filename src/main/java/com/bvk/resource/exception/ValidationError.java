package com.bvk.resource.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}


	public void addError(String fieldName, String messages) {
		getErrors().add(new FieldMessage(fieldName, messages));
	}


	public List<FieldMessage> getErrors() {
		return errors;
	}
}
