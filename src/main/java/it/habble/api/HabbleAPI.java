package it.habble.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import it.habble.api.error.ErrorHandler;
import it.habble.api.error.HabbleException;

public class HabbleAPI  {
	
	private RestTemplate restTemplate;
	private ObjectMapper jsonMapper;
	private HttpHeaders requestHeaders;
	private Properties properties;
	private String clientId, clientSecret;
	
	public HabbleAPI() {
		restTemplate = createRestTemplate();
		loadProperties();
	}
	
	
	private RestTemplate createRestTemplate() {
		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters()
		        .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		restTemplate.setErrorHandler(new ErrorHandler());
		return restTemplate;
	}
	
	/**
	 * Prints headers received by server
	 * @param entity
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private void printHeaders(ResponseEntity entity) {
	    for (Map.Entry<String, List<String>> entry : entity.getHeaders().entrySet()) {
		    String key = entry.getKey();
		    String val = StringUtils.collectionToDelimitedString(entry.getValue(), ",");
		    System.out.println("KEY: " + key + ", VAL: " + val);
		}
	}
	
	/**
	 * Simple POST request for retrieving the raw JSON response
	 * @param url it's the endpoint
	 * @param method the HTTP method
	 * @param entity the entity to be serialized
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private ResponseEntity<String> getResponse(String url, HttpMethod method, HttpEntity<String> entity) {
		ResponseEntity<String> rawResponse = restTemplate.exchange(url, method, entity, String.class, Collections.EMPTY_MAP);

		System.out.println("HTTP code: " + rawResponse.getStatusCode().value() + ", body: " + 
							 rawResponse.getBody() + ", headers: ");
		return rawResponse;
	}
	
	/**
	 * Shortcut for creating the HTTP entity request with JSON headers
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 */
	@SuppressWarnings("unused")
	private HttpEntity<String> stringEntity(Object obj) throws JsonProcessingException {
	    //Creating http entity object with request body and headers
	    HttpEntity<String> httpEntity = 
	        new HttpEntity<String>(getJsonMapper().writeValueAsString(obj), getJsonHeaders());
	    return httpEntity;
	}
	
	/**
	 * Builds a Jackson mapper, avoiding NULL serialization
	 * @return
	 */
	private ObjectMapper getJsonMapper() {
		if(jsonMapper == null)
			jsonMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
							.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
							.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
							.enable(SerializationFeature.INDENT_OUTPUT)
							.setSerializationInclusion(Include.NON_NULL);
		
		return jsonMapper;
	}
	
	private HttpHeaders getJsonHeaders() {
		// headers
		requestHeaders = new HttpHeaders();
	    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
	    requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    return requestHeaders;
	}
	
	private void loadProperties() {
	
		InputStream input = null;
	
		try {
	
			input = new FileInputStream("application.properties");
			// load a properties file
			properties.load(input);
			
			clientId = properties.getProperty("client_id");
			clientSecret = properties.getProperty("client_secret");
			
			if(clientId == null || clientSecret == null)
				throw new HabbleException("Missing credentials", 
										  "You have to specify your credentials inside application.properties");
	
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
