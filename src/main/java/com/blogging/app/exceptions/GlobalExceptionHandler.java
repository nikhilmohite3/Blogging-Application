package com.blogging.app.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {

		String exceptionMessage = exception.getMessage();
		Map<String, Boolean> apiResponse = new HashMap<>();
		apiResponse.put(exceptionMessage, false);
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> apiResponse = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach(err -> {
			String field = ((FieldError)err).getField();
			String message = err.getDefaultMessage();
			apiResponse.put(field, message);
		});
		
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}
}
