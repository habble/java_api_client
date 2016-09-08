package it.habble.api.request;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.habble.api.HabbleAPI;
import it.habble.api.endpoints.Endpoint;
import it.habble.api.entity.Department;
import it.habble.api.entity.RestError;
import it.habble.api.entity.filter.Paginator;

public class DepartmentRequest extends HabbleAPI {
	
	public DepartmentRequest() throws Exception {}

	public ResponseEntity<List<Department>> getDepartments(Paginator paginator) {
		return oauthTemplate.exchange(paginator.build(Endpoint.DEPARTMENTS_LIST.toString()), 
									  HttpMethod.GET, null, 
									  new ParameterizedTypeReference<List<Department>>() {});
	}

	public ResponseEntity<List<Department>> getDepartments() {
		return getDepartments(new Paginator());
	}
	
	public ResponseEntity<Department> getById(Integer id) {
		return	oauthTemplate.exchange(Endpoint.DEPARTMENT.getId(id), 
		                          HttpMethod.GET, null, Department.class);
	}
	
	/*
	 * Inserts a single department entity
	 * @param c
	 * @return
	 * @throws RestClientException
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<Department> insert(Department c) throws RestClientException, JsonProcessingException {
		return oauthTemplate.exchange(Endpoint.DEPARTMENT.toString(), HttpMethod.PUT, 
							   			stringEntity(c), Department.class);
	}
	
	/**
	 * Updates a single contact entity
	 * @param c
	 * @return an empty message with HTTP 200 status code if the update was successful, otherwise
	 * a {@link RestError}
	 * @throws RestClientException
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<Department> update(Department c) throws RestClientException, JsonProcessingException {
		return oauthTemplate.exchange(Endpoint.DEPARTMENT.toString(), HttpMethod.POST, 
							   		  stringEntity(c), Department.class);
	}
	
	/**
	 * Inserts a list of contacts
	 * @param <T>
	 * @param list
	 * @return the total number of successfully inserted contacts
	 * @throws JsonProcessingException 
	 * @throws RestClientException 
	 */
	public ResponseEntity<Integer> insert(List<Department> list) throws RestClientException, JsonProcessingException {
		return oauthTemplate.exchange(Endpoint.DEPARTMENTS_LIST.toString(), HttpMethod.PUT, 
									  stringEntity(list), Integer.class);
	}
	
	/**
	 * Deletes a single contact entity
	 * @param id the contac's identifier
	 * @return
	 */
	public ResponseEntity<String> delete(Integer id) {
		return oauthTemplate.exchange(Endpoint.DEPARTMENT.getId(id), 
				   HttpMethod.DELETE, null, String.class);
	}	
}
