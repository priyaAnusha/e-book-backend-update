package com.ebook.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
//	@ExceptionHandler(IdNotFoundException.class)
//	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(IdNotFoundException res){
//		String message=res.getMessage();
//		ApiResponse apiResponse=new ApiResponse(message,false);
//		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
//	}
//		
//	@ExceptionHandler(InvalidUserException.class)
//	public ResponseEntity<ApiResponse> userNotFoundExceptionHandler(InvalidUserException res){
//		String message=res.getMessage();
//	
//		ApiResponse apiResponse=new ApiResponse(message,false);
//		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
//	}
	@ExceptionHandler({InvalidUserException.class,
			IdNotFoundException.class,NoBookException.class,InvalidInputException.class,AlreadyExsistException.class})
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(Exception res){
		String message=res.getMessage();
		System.out.println(message+" this is error");
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgNotValidException(MethodArgumentNotValidException ex){
	    String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	    return new ResponseEntity<String>(defaultMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = DoesNotExistsException.class)
	public ResponseEntity<Object> handleNotFoundException(DoesNotExistsException exception, WebRequest webRequest) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(404);
		exceptionResponse.setTime(LocalDateTime.now());
		exceptionResponse.setMessage(exception.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		}
}
