package com.springproject.app.ws.exceptions;

//class handles exceptions

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springproject.app.ws.ui.model.response.ErrorMessage;



@ControllerAdvice                                                          //Enables class to listen to exceptions from other classes
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

	
    @ExceptionHandler(value = {Exception.class})                                            //annotation handles exceptions                               
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {    //method handles general exceptions, accepts parameter arguments
    	    	
    	String errorMessageDescription = ex.getLocalizedMessage();
    	    	
    	if(errorMessageDescription == null) errorMessageDescription = ex.toString();
    	
    	ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
    	
    	return new ResponseEntity<>(
    			errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    	
    }
    
    @ExceptionHandler(value = { NullPointerException.class, UserServiceException.class })                //method handles NullpointerExcetions and UserServiceExceptions 
    public ResponseEntity<Object> handleSpecificExceptions(Exception ex, WebRequest request) {
        
    	String errorMessageDescription = ex.getLocalizedMessage();
    	
    	if(errorMessageDescription == null) errorMessageDescription = ex.toString();
    	
    	ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
    	
    	return new ResponseEntity<>(
    			errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    	
    }
    
    
    
}