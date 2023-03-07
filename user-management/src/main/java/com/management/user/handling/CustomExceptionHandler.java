package com.management.user.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	 @ExceptionHandler(UserNotFound.class)
	  public final ResponseEntity<UserErrorResponse> handleUserNotFoundException
	            (UserNotFound ex) 
	  {
	    UserErrorResponse error = new UserErrorResponse(404, ex.getMessage());
	    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	  }
}
