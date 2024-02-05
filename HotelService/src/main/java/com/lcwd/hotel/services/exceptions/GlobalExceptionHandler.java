package com.lcwd.hotel.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lcwd.hotel.service.payload.ApiResponse;
import com.lcwd.hotel.service.payload.ApiResponse.ApiResponseBuilder;

@RestControllerAdvice //annotation that is annotated with both @ControllerAdvice and @ResponseBody , 
//which essentially means @ExceptionHandler methods 
//are rendered to the response body through message conversion (versus view resolution or template rendering),
//so @ControllerAdvice can be used even for REST
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex)
	{
		String msg=ex.getMessage();
		ApiResponse response=ApiResponse.builder().message(msg).success(true).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
}
