package com.grego.support.exception.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.grego.support.exception.enums.JsonTimeStampSerializer;

public class GenericResponse {

	private boolean success;
	private Object data;
	private List<ErrorInfo> errors;
	@JsonSerialize(using = JsonTimeStampSerializer.class)
	private Date lastUpdate;

	public GenericResponse() {
		this.errors = new ArrayList<>();
	}

	public void addError(String message, String key, String description) {
		errors.add(new ErrorInfo(message, key, description));
	}

	public void addError(String key, String description) {
		this.addError(null, key, description);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<ErrorInfo> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorInfo> errors) {
		this.errors = errors;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GenericResponse [success=");
		builder.append(success);
		builder.append(", data=");
		builder.append(data);
		builder.append(", errors=");
		builder.append(errors);
		builder.append(", lastUpdate=");
		builder.append(lastUpdate);
		builder.append("]");
		return builder.toString();
	}
}
