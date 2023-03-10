package com.prefeitura.selecaoBack.controller.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.UnexpectedTypeException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.prefeitura.selecaoBack.domain.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(InvalidDefinitionException.class)
	public ResponseEntity<StandardError> objectNotFound(InvalidDefinitionException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<StandardError> dataIntegrity(DateTimeParseException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityViolationException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}


	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<StandardError> dataIntegrity(HttpRequestMethodNotSupportedException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}


	@ExceptionHandler(UnexpectedTypeException.class)
	public ResponseEntity<StandardError> dataIntegrity(UnexpectedTypeException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseEntity<StandardError> dataIntegrity(MissingPathVariableException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError err = new ValidationError(HttpStatus.NOT_FOUND.value(),  "Error de valida????o", LocalDateTime.now());
		
		for (FieldError x: e.getBindingResult().getFieldErrors()) {
			err.addError( x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
}
