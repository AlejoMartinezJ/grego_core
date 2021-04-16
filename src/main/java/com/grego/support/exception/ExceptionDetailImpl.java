package com.grego.support.exception;

import com.grego.support.exception.enums.ExceptionDetail;

public class ExceptionDetailImpl implements ExceptionDetail {

	private String key;
	private String description;

	public ExceptionDetailImpl(String key, String description) {
		this.key = key;
		this.description = description;
	}

	@Override
	public String getKey() {

		return key;
	}

	@Override
	public String getDescription() {

		return description;
	}
}
