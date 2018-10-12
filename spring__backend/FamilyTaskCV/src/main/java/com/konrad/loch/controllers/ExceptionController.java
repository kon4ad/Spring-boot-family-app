package com.konrad.loch.controllers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(value = { EmptyResultDataAccessException.class })
	protected ResponseEntity<Object> handleEmptyQuery(Exception ex, WebRequest request) {
		logger.error(ex.getMessage());
		return handleExceptionInternal(null, null, new HttpHeaders(), HttpStatus.OK, request);
	}
	
	
	@ExceptionHandler(value = { SaveOperationException.class })
	protected ResponseEntity<Object> handleSaveError(Exception ex, WebRequest request) {
		logger.error(ex.getMessage());
		return handleExceptionInternal(ex, "Cannot save this enitity to database.", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(value = {Exception.class})
	protected ResponseEntity<Object> handleSQLError(Exception ex, WebRequest request){
		logger.error(ex.getMessage());
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
