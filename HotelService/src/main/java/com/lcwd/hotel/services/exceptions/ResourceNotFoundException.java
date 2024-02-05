package com.lcwd.hotel.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String exceptionName)
	{
		super(exceptionName);
	}
	
	public ResourceNotFoundException(){
        super("Resource not found !!");
    }
}
