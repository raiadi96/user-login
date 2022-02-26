package com.example.userlogin.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.userlogin.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> handleServiceExcption(UserServiceException exception, WebRequest request){
		ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR );
		
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleServiceExcption(Exception exception, WebRequest request){
		ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR );
		
	}
}
	
