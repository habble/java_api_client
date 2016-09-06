package it.habble.api.request;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import it.habble.api.HabbleAPI;
import it.habble.api.endpoints.Endpoint;
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
	
	public ResponseEntity<Site> getSite(Integer id) {
		return	oauthTemplate.exchange(Endpoint.SITE.getId(id), 
		                          HttpMethod.GET, null, Site.class);
	}
}
