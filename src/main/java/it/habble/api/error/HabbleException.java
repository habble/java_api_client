package it.habble.api.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HabbleException extends RuntimeException {
	private static final long serialVersionUID = 7213854898078178640L;
	private it.habble.api.error.RestError error;
	private Integer httpStatus;
	
	public HabbleException(String errorTitle, String errorDescription) {
		error = new RestError(errorTitle, errorDescription);
	}

	public HabbleException() {
		super();
	}
	
	public HabbleException(Throwable t) {
		super(t);
	}
	
	public HabbleException(it.habble.api.error.RestError error, Integer status) {
		this.error = error;
		this.httpStatus = status;
	}

	public it.habble.api.error.RestError getError() {
		return this.error;
	}

	public void setError(it.habble.api.error.RestError error) {
		this.error = error;
	}
	

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public String toString() {
		if(this.error != null) {
			try {
				return new ObjectMapper().writeValueAsString(this.error);
			} catch (JsonProcessingException e) {
				return "";
			}
		} else {
			return "Null error";
		}
	}
}
