package com.technohacks.srms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.technohacks.srms.util.ResponseStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleEmailNotFoundException(EmailNotFoundException ex) {
		ResponseStructure<String> structure= new ResponseStructure<>() ;
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Student with given email not found");
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
