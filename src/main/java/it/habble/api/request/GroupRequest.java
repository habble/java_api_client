package it.habble.api.request;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.habble.api.HabbleAPI;
import it.habble.api.endpoints.Endpoint;
import it.habble.api.entity.Group;
import it.habble.api.entity.RestError;
import it.habble.api.entity.filter.Paginator;

public class GroupRequest extends HabbleAPI {
	
	public GroupRequest() throws Exception {}

	public ResponseEntity<List<Group>> getGroups(Paginator paginator) {
		return oauthTemplate.exchange(paginator.build(Endpoint.GROUPS_LIST.toString()), 
									  HttpMethod.GET, null, 
									  new ParameterizedTypeReference<List<Group>>() {});
	}

	public ResponseEntity<List<Group>> getGroups() {
		return getGroups(new Paginator());
	}
	
	public ResponseEntity<Group> getById(Integer id) {
		return	oauthTemplate.exchange(Endpoint.GROUP.getId(id), 
		                          HttpMethod.GET, null, Group.class);
	}
	
	/**
	 * Inserts a single group entity
	 * @param c
	 * @return
	 * @throws RestClientException
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<Group> insert(Group g) throws RestClientException, JsonProcessingException {
		return oauthTemplate.exchange(Endpoint.GROUP.toString(), HttpMethod.PUT, 
							   			stringEntity(g), Group.class);
	}
	
	/**
	 * Updates a single group entity
	 * @param c
	 * @return an empty message with HTTP 200 status code if the update was successful, otherwise
	 * a {@link RestError}
	 * @throws RestClientException
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<Group> update(Group g) throws RestClientException, JsonProcessingException {
		return oauthTemplate.exchange(Endpoint.GROUP.toString(), HttpMethod.POST, 
							   		  stringEntity(g), Group.class);
	}
	
	/**
	 * Inserts a list of contacts
	 * @param <T>
	 * @param list
	 * @return the total number of successfully inserted contacts
	 * @throws JsonProcessingException 
	 * @throws RestClientException 
	 */
	public ResponseEntity<Integer> insert(List<Group> list) throws RestClientException, JsonProcessingException {
		return oauthTemplate.exchange(Endpoint.GROUPS_LIST.toString(), HttpMethod.PUT, 
									  stringEntity(list), Integer.class);
	}
	
	/**
	 * Deletes a single contact entity
	 * @param id the contac's identifier
	 * @return
	 */
	public ResponseEntity<String> delete(Integer id) {
		return oauthTemplate.exchange(Endpoint.GROUP.getId(id), 
				   HttpMethod.DELETE, null, String.class);
	}
}
