package it.habble.api.entity;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"status"})
public class RestError {
	@JsonProperty(value = "error")
	private String error;
	
	@JsonProperty(value = "errorDescription")
	private String errorDescription;

	private HttpStatus status;
	
	public RestError() {}
	
	public RestError(String error, String desc) {
		this.error = error;
		this.errorDescription = desc;
	}
	
	public RestError(String error, String desc, HttpStatus status) {
		this.error = error;
		this.errorDescription = desc;
		this.status = status;
	}
	
	public String getError() {
		return this.error;
	}
	
	public RestError setError(String error) {
		this.error = error;
		return this;
	}
	
	public String getErrorDescription() {
		return this.errorDescription;
	}
	
	public RestError setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
		return this;
	}

	/**
	 * @return the HTTP statusCode
	 */
	public HttpStatus getStatus() {
		return this.status;
	}

	/**
	 * @param statusCode the HTTP statusCode to set
	 */
	public RestError setStatus(HttpStatus status) {
		this.status = status;
		return this;
	}
}
