package com.definex.loanapplication.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptioanHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handle(ResourceNotFoundException exception){
		
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(IdentityNoOrPhoneNumberAllreadyExists.class)
	public ResponseEntity<String> handle(IdentityNoOrPhoneNumberAllreadyExists exception){
		
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handle(MethodArgumentNotValidException exception){
		
		List<String> errors = new ArrayList<>();
		exception.getBindingResult().getAllErrors().forEach((error)->{
			String message = error.getDefaultMessage();
			errors.add(message);
		});
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
