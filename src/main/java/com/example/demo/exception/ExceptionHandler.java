package com.example.demo.exception;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.config.ResponseStructure;

//exception handle annotation
@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler
{
	//@ExceptionHandler -----> annotation
	 @org.springframework.web.bind.annotation.ExceptionHandler
      public ResponseEntity<ResponseStructure<String>> productNotFound(BookNotFound exception)
      {
    	  ResponseStructure<String> structure = new ResponseStructure<>();
    	  structure.setStatus(HttpStatus.NOT_FOUND.value());
    	  structure.setMessage("book not found");
    	  structure.setData(exception.getMessage());
    	  
    	  return new ResponseEntity<ResponseStructure<String>> (structure, HttpStatus.NOT_FOUND);
      }
//	 type------->  handleMethod  ctrl+space enter -----create metghod
	 @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		 
		 
		   List<ObjectError> listError = ex.getAllErrors();
		   HashMap<String,String> hm = new HashMap<>();
		   
		   for(ObjectError error : listError)
		   {
			   String message = error.getDefaultMessage();
			   String fieldName = ((FieldError)error).getField();
			   hm.put(message, fieldName);
			   
		   }
		  return new ResponseEntity<Object> (hm,HttpStatus.BAD_REQUEST);
		 
		 
		 
		// TODO Auto-generated method stub
//		return super.handleMethodArgumentNotValid(ex, headers, status, request);
		                 //            ex -----> List<objectError>
	}
}
