package com.Travel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException extends RuntimeException {

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public ResponseEntity<ErrorDetails> handleException(MethodArgumentNotValidException ex, WebRequest request) {
//		List<String> error = new ArrayList<String>();
//
//		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
//
//			error.add(fieldError.getDefaultMessage());
//		}
//
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), error, request.getDescription(false));
//
//		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//
//	}

	@ExceptionHandler(value = ValidationException.class)

	public ResponseEntity<String> globalEntity(Exception exception) {

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		String errorMessage = "Validation failed for object: "
				+ ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

}
