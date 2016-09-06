package it.habble.api.request;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import it.habble.api.HabbleAPI;
import it.habble.api.endpoints.Endpoint;
import it.habble.api.entity.Contact;
import it.habble.api.entity.filter.Paginator;

public class ContactRequest extends HabbleAPI {
	
	public ContactRequest() throws Exception {}

	public ResponseEntity<List<Contact>> getContacts(Paginator paginator) {
		return oauthTemplate.exchange(paginator.build(Endpoint.CONTACTS_LIST.toString()), 
									  HttpMethod.GET, null, 
									  new ParameterizedTypeReference<List<Contact>>() {});
	}
	
	public ResponseEntity<List<Contact>> getContacts() {
		return getContacts(new Paginator());
	}

	
	public ResponseEntity<Contact> getContact(Integer id) {
		return	oauthTemplate.exchange(Endpoint.CONTACT.getId(id), 
		                          HttpMethod.GET, null, Contact.class);
	}
}
