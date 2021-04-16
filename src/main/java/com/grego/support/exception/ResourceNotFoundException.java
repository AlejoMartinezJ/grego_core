package com.grego.support.exception;

import com.grego.support.exception.enums.GenericExceptionDetail;

public class ResourceNotFoundException extends GenericException{

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super(GenericExceptionDetail.GENERIC_NOT_FOUND_EXCEPTION);
		System.err.println("ResourceNotFoundException");
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause, GenericExceptionDetail.GENERIC_NOT_FOUND_EXCEPTION);
		System.err.println("ResourceNotFoundException1");

	}

	public ResourceNotFoundException(String message) {
		super(message, GenericExceptionDetail.GENERIC_NOT_FOUND_EXCEPTION);
		System.err.println("ResourceNotFoundException2");

	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause, GenericExceptionDetail.GENERIC_NOT_FOUND_EXCEPTION);
		System.err.println("ResourceNotFoundException3");

	}
}
