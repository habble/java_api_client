package it.habble.api.request;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.habble.api.HabbleAPI;
import it.habble.api.endpoints.Endpoint;
import it.habble.api.entity.RestError;
import it.habble.api.entity.Site;
import it.habble.api.entity.filter.Paginator;

public class SiteRequest extends HabbleAPI {
	
	public SiteRequest() throws Exception {}

	public ResponseEntity<List<Site>> getSites(Paginator paginator) {
		return oauthTemplate.exchange(paginator.build(Endpoint.SITES_LIST.toString()), 
									  HttpMethod.GET, null, 
									  new ParameterizedTypeReference<List<Site>>() {});
	}

	public ResponseEntity<List<Site>> getSites() {
		return getSites(new Paginator());
	}
	
	public ResponseEntity<Site> getById(Integer id) {
		return	oauthTemplate.exchange(Endpoint.SITE.getId(id), 
		                          HttpMethod.GET, null, Site.class);
	}
	
	/**
	 * Inserts a single site entity
	 * @param c
	 * @return
	 * @throws RestClientException
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<Site> insert(Site c) throws RestClientException, JsonProcessingException {
		return oauthTemplate.exchange(Endpoint.SITE.toString(), HttpMethod.PUT, 
							   			stringEntity(c), Site.class);
	}
	
	/**
	 * Updates a single site entity
	 * @param c
	 * @return an empty message with HTTP 200 status code if the update was successful, otherwise
	 * a {@link RestError}
	 * @throws RestClientException
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<Site> update(Site c) throws RestClientException, JsonProcessingException {
		return oauthTemplate.exchange(Endpoint.SITE.toString(), HttpMethod.POST, 
							   		  stringEntity(c), Site.class);
	}
	
	/**
	 * Inserts a list of sites
	 * @param <T>
	 * @param list
	 * @return the total number of successfully inserted sites
	 * @throws JsonProcessingException 
	 * @throws RestClientException 
	 */
	public ResponseEntity<Integer> insert(List<Site> list) throws RestClientException, JsonProcessingException {
		return oauthTemplate.exchange(Endpoint.SITES_LIST.toString(), HttpMethod.PUT, 
									  stringEntity(list), Integer.class);
	}
	
	/**
	 * Deletes a single site entity
	 * @param id the site's identifier
	 * @return
	 */
	public ResponseEntity<String> delete(Integer id) {
		return oauthTemplate.exchange(Endpoint.SITE.getId(id), 
				   HttpMethod.DELETE, null, String.class);
	}
}
