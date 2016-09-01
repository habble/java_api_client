package it.habble.api.error;


import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ErrorHandler implements ResponseErrorHandler {
	
	private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return errorHandler.hasError(response);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		String error = IOUtils.toString(response.getBody());
		Integer value = response.getStatusCode().value();
		System.out.println("Catched error: " + error + ": " + value);
		
		// Customization of default OAuth errors, no better way at the moment
		if(error.contains("error_description")) {
			error = error.replace("error_description", "errorDescription");
		}
		
		RestError errObject = new ObjectMapper().readValue(error, RestError.class);
		errObject.setStatus(HttpStatus.valueOf(value));
		throw new HabbleException(errObject, value);
	}
}
