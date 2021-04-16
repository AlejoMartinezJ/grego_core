package com.grego.support.exception;

import org.apache.commons.lang.StringUtils;

import com.grego.support.exception.enums.ExceptionDetail;

public class GenericException extends Exception{

	private static final long serialVersionUID = 1L;
	private ExceptionDetail exceptionDetail;
	
	public GenericException(ExceptionDetail exceptionDetail) {
		super(exceptionDetail.getDescription());
		System.err.println("GenericException");
		this.exceptionDetail = exceptionDetail;
	}
	
	public GenericException(String message, ExceptionDetail exceptionDetail,String...values) {
		super(message);
		this.exceptionDetail = getCustomDetail(exceptionDetail, values);
	}
	
	public GenericException(ExceptionDetail exceptionDetail,String...values) {
		this.exceptionDetail = getCustomDetail(exceptionDetail, values);
	}
	
	public GenericException(Throwable cause) {
		super(cause);
	}
	
	public GenericException(String message, Throwable cause, ExceptionDetail exceptionDetail) {
		super(message, cause);
		this.exceptionDetail = exceptionDetail;
	}
	
	public GenericException(String message, ExceptionDetail exceptionDetail) {
		super(message);
		this.exceptionDetail = exceptionDetail;
	}
	
	public GenericException(Throwable cause, ExceptionDetail exceptionDetail) {
		super(cause);
		this.exceptionDetail = exceptionDetail;
	}

	public ExceptionDetail getExceptionDetail() {
		return exceptionDetail;
	}

	public void setExceptionDetail(ExceptionDetail exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}	
	
	private ExceptionDetail getCustomDetail(ExceptionDetail exDetail,String... values) {	
		return new ExceptionDetailImpl(exDetail.getKey(),generateCustomMessage(exDetail.getDescription(),values));		
	}

	private String generateCustomMessage(String description,String[] values) {
		int sizeValues = values.length;
		String message = new String(description);
		for(int i=0;i<sizeValues;i++) {
			message = StringUtils.replace(message, "{"+i+"}", values[i]);
		}
		return message;
	}	
}
