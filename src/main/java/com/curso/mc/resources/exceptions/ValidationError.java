package com.curso.mc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandarError {

	private static final long serialVersionUID = 1L;
	private List<FieldMessage> listError = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getError() {
		return listError;
	}

	public void addError(String fieldMessage, String message) {
		listError.add(new FieldMessage(fieldMessage, message));
	}

}
