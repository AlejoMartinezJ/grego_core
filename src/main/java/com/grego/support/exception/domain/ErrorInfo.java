package com.grego.support.exception.domain;

public class ErrorInfo {

	private String message;
	private String key;
	private String description;

	public ErrorInfo(String message, String key, String description) {
		super();
		this.message = message;
		this.key = key;
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ErrorInfo [message=" + message + ", key=" + key + ", description=" + description + "]";
	}
}
