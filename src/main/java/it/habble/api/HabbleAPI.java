package it.habble.api;

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
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import it.habble.api.endpoints.Endpoint;
import it.habble.api.request.CallRequest;
import it.habble.api.request.ContactRequest;
import it.habble.api.request.DepartmentRequest;
import it.habble.api.request.GroupRequest;
import it.habble.api.request.SiteRequest;

public class HabbleAPI  {
	
	protected RestTemplate noAuthTemplate;
	protected OAuth2RestTemplate oauthTemplate;
	protected ObjectMapper jsonMapper;
	protected HttpHeaders requestHeaders;
	protected Properties properties;
	private String clientId, clientSecret;
	
	// entities wrappers
	private CallRequest callRequest;
	private ContactRequest contactRequest;
	private DepartmentRequest depRequest;
	private SiteRequest sitesRequest;
	private GroupRequest groupsRequest;
	
	public HabbleAPI() throws Exception {
		this(false);
	}
	
	public HabbleAPI(Boolean forAgent) throws Exception {
		if(!forAgent) {
			loadProperties();
			initRestTemplates();
		} else {
			createRestTemplate();
		}
	}

	/**
	 * Object for accessing calls' methods
	 * @return
	 * @throws Exception
	 */
	public CallRequest calls() throws Exception {
		if(callRequest == null)
			callRequest = new CallRequest();
		
		return callRequest;
	}
	
	/**
	 * Object for accessing contacts' methods
	 * @return
	 * @throws Exception
	 */
	public ContactRequest contacts() throws Exception {
		if(contactRequest == null)
			contactRequest = new ContactRequest();
		
		return contactRequest;
	}
	
	/**
	 * Object for accessing departments' methods
	 * @return
	 * @throws Exception
	 */
	public DepartmentRequest departments() throws Exception {
		if(depRequest == null)
			depRequest = new DepartmentRequest();
		
		return depRequest;
	}
	
	/**
	 * Object for accessing groups' methods
	 * @return
	 * @throws Exception
	 */
	public GroupRequest groups() throws Exception {
		if(groupsRequest == null)
			groupsRequest = new GroupRequest();
		
		return groupsRequest;
	}
	
	/**
	 * Object for accessing sites' methods
	 * @return
	 * @throws Exception
	 */
	public SiteRequest sites() throws Exception {
		if(sitesRequest == null)
			sitesRequest = new SiteRequest();
		
		return sitesRequest;
	}
	
	private HabbleAPI createRestTemplate() {
		noAuthTemplate = new RestTemplate();
		noAuthTemplate.getMessageConverters()
		        .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return this;
	}
	
	private HabbleAPI createOAuthRestTemplate(String clientId, String clientSecret) {
		ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setClientAuthenticationScheme(AuthenticationScheme.header);
        resource.setId("api");
        resource.setClientId(clientId);
        resource.setClientSecret(clientSecret);
        resource.setAccessTokenUri(Endpoint.tokenUrl());
        oauthTemplate = new OAuth2RestTemplate(resource);
        
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        return this;
	}
	
	/**
	 * Creates HTTP REST templates for authorized and non-authorized requests
	 * @return
	 */
	private void initRestTemplates() {
		if(oauthTemplate != null)
			return; // already inited
		
		System.out.println("HabbleAPI.initRestTemplates()");
		createRestTemplate();
		createOAuthRestTemplate(clientId, clientSecret);
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
	@SuppressWarnings({ "unchecked" })
	public ResponseEntity<String> getNoAuthResponse(String url, HttpMethod method, HttpEntity<String> entity) {
		ResponseEntity<String> rawResponse = noAuthTemplate.exchange(url, method, entity, 
											 String.class, Collections.EMPTY_MAP);

		System.out.println("HTTP code: " + rawResponse.getStatusCode().value() + ", body: " + 
							 rawResponse.getBody() + ", headers: ");
		return rawResponse;
	}
	
	@SuppressWarnings({ "unchecked" })
	public <T> ResponseEntity<T> getNoAuthResponse(String url, HttpMethod method, HttpEntity<String> entity, Class<T> ct) {
		ResponseEntity<T> rawResponse = noAuthTemplate.exchange(url, method, entity, 
												ct, Collections.EMPTY_MAP);

		System.out.println("HTTP code: " + rawResponse.getStatusCode().value() + ", body: " + 
							 rawResponse.getBody() + ", headers: ");
		return rawResponse;
	}
	
	/**
	 * Simple POST request for retrieving the raw JSON response
	 * @param url it's the endpoint
	 * @param method the HTTP method
	 * @param entity the entity to be serialized
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public ResponseEntity<String> getOAuthResponse(String url, HttpMethod method, HttpEntity<String> entity) {
		ResponseEntity<String> rawResponse = oauthTemplate.exchange(url, method, entity, 
											 String.class, Collections.EMPTY_MAP);

		System.out.println("HTTP code: " + rawResponse.getStatusCode().value() + ", body: " + 
							 rawResponse.getBody() + ", headers: ");
		return rawResponse;
	}
	
	public RestTemplate getNoAuthTemplate() {
		return noAuthTemplate;
	}

	public HabbleAPI setNoAuthTemplate(RestTemplate noAuthTemplate) {
		this.noAuthTemplate = noAuthTemplate;
		return this;
	}

	public OAuth2RestTemplate getOauthTemplate() {
		return oauthTemplate;
	}

	public HabbleAPI setOauthTemplate(OAuth2RestTemplate oauthTemplate) {
		this.oauthTemplate = oauthTemplate;
		return this;
	}

	@SuppressWarnings({ "unchecked" })
	public <T> ResponseEntity<T> getOAuthResponse(String url, HttpMethod method, HttpEntity<String> entity, Class<T> ct) {
		ResponseEntity<T> rawResponse = oauthTemplate.exchange(url, method, entity, 
												ct, Collections.EMPTY_MAP);

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
	public HttpEntity<String> stringEntity(Object obj) throws JsonProcessingException {
	    //Creating http entity object with request body and headers
	    HttpEntity<String> httpEntity = 
	        new HttpEntity<String>(getJsonMapper().writeValueAsString(obj), getJsonHeaders());
	    return httpEntity;
	}
	
	@SuppressWarnings("rawtypes")
	public <T> ResponseEntity<T> oAuthPostForEntity(String url, HttpEntity httpEntity, Class<T> clazz) {
		return oauthTemplate.postForEntity(url, httpEntity, clazz);
	}
	
	public <T> ResponseEntity<T> oAuthGetForEntity(String url, Class<T> clazz) {
		return oauthTemplate.getForEntity(url, clazz);
	}
	
	@SuppressWarnings("rawtypes")
	public <T> ResponseEntity<T> noOAuthPostForEntity(String url, HttpEntity httpEntity, Class<T> clazz) {
		return noAuthTemplate.postForEntity(url, httpEntity, clazz);
	}
	
	public <T> ResponseEntity<T> noOAuthGetForEntity(String url, Class<T> clazz) {
		return noAuthTemplate.getForEntity(url, clazz);
	}
	
	/**
	 * Builds a Jackson mapper, avoiding NULL serialization
	 * @return
	 */
	public ObjectMapper getJsonMapper() {
		if(jsonMapper == null)
			jsonMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
							.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
							.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
							.enable(SerializationFeature.INDENT_OUTPUT)
							.setSerializationInclusion(Include.NON_NULL);
		
		return jsonMapper;
	}
	
	/**
	 * Sets JSON headers for HTTP requests
	 * @return
	 */
	protected HttpHeaders getJsonHeaders() {
		// headers
		if(requestHeaders == null) {
			requestHeaders = new HttpHeaders();
		    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		    requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		}
	    return requestHeaders;
	}
	
	/**
	 * Initialization of paths and credentials
	 * @throws Exception 
	 */
	private void loadProperties() throws Exception {
		
		if(properties != null)
			return;		// already inited
		
		System.out.println("HabbleAPI.loadProperties()");
	
		InputStream input = null;
	
		try {
	
			input = this.getClass().getClassLoader()
						.getResourceAsStream("application.properties");
			if(input == null)
				throw new Exception("Missing configuration: cannot find application.properties");
			
			// load a properties file
			properties = new Properties();
			properties.load(input);
			
			clientId = properties.getProperty("client_id");
			clientSecret = properties.getProperty("client_secret");
			
			if(clientId == null || clientId.trim().isEmpty() ||
				clientSecret == null || clientSecret.trim().isEmpty())
				throw new Exception("Missing credentials: you have to specify your credentials inside application.properties");

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
	
	public String toJson(Object o) {
		try {
			return getJsonMapper().writeValueAsString(o);
		} catch (JsonProcessingException e) {
			return "Cannot parse json object";
		}
	}
	
	
}
