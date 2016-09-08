package it.habble.api.request;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.habble.api.HabbleAPI;
import it.habble.api.endpoints.Endpoint;
import it.habble.api.entity.Contact;
import it.habble.api.entity.RestError;
import it.habble.api.entity.filter.Paginator;

public class ContactRequest extends HabbleAPI {
	
	public ContactRequest() throws Exception {}

	/**
	 * Gets all customer's contacts sorted and limited by {@link Paginator} 
	 * @param paginator
	 * @return
	 */
	public ResponseEntity<List<Contact>> getContacts(Paginator paginator) {
		return oauthTemplate.exchange(paginator.build(Endpoint.CONTACTS_LIST.toString()), 
									  HttpMethod.GET, null, 
									  new ParameterizedTypeReference<List<Contact>>() {});
	}
	
	/**
	 * Gets all customer's contacts
	 * @param paginator
	 * @return
	 */
	public ResponseEntity<List<Contact>> getContacts() {
		return getContacts(new Paginator());
	}

	/**
	 * Gets a specific contact
	 * @param id it's the contact identifier
	 * @return
	 */
	public ResponseEntity<Contact> getById(Integer id) {
		return	oauthTemplate.exchange(Endpoint.CONTACT.getId(id), 
		                          HttpMethod.GET, null, Contact.class);
	}
	
	/**
	 * Inserts a single contact entity
	 * @param c
	 * @return
	 * @throws RestClientException
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<Contact> insert(Contact c) throws RestClientException, JsonProcessingException {
		return oauthTemplate.exchange(Endpoint.CONTACT.toString(), HttpMethod.PUT, 
							   			stringEntity(c), Contact.class);
	}
	
	/**
	 * Updates a single contact entity
	 * @param c
	 * @return an empty message with HTTP 200 status code if the update was successful, otherwise
	 * a {@link RestError}
	 * @throws RestClientException
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<Contact> update(Contact c) throws RestClientException, JsonProcessingException {
		return oauthTemplate.exchange(Endpoint.CONTACT.toString(), HttpMethod.POST, 
							   		  stringEntity(c), Contact.class);
	}
	
	/**
	 * Inserts a list of contacts
	 * @param <T>
	 * @param list
	 * @return the total number of successfully inserted contacts
	 * @throws JsonProcessingException 
	 * @throws RestClientException 
	 */
	public ResponseEntity<Integer> insert(List<Contact> list) throws RestClientException, JsonProcessingException {
		return oauthTemplate.exchange(Endpoint.CONTACTS_LIST.toString(), HttpMethod.PUT, 
									  stringEntity(list), Integer.class);
	}
	
	/**
	 * Deletes a single contact entity
	 * @param id the contac's identifier
	 * @return
	 */
	public ResponseEntity<String> delete(Integer id) {
		return oauthTemplate.exchange(Endpoint.CONTACT.getId(id), 
				   HttpMethod.DELETE, null, String.class);
	}
}