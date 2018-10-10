package com.konrad.loch.controllers;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.konrad.loch.exceptions.SaveOperationException;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { EmptyResultDataAccessException.class })
	protected ResponseEntity<Object> handleEmptyQuery(Exception ex, WebRequest request) {
		return handleExceptionInternal(null, null, new HttpHeaders(), HttpStatus.OK, request);
	}
	
	
	@ExceptionHandler(value = { SaveOperationException.class })
	protected ResponseEntity<Object> handleSaveError(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
	}
}
