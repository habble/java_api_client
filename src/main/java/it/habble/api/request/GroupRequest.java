package it.habble.api.request;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import it.habble.api.HabbleAPI;
import it.habble.api.endpoints.Endpoint;
import it.habble.api.entity.Group;
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
	
	public ResponseEntity<Group> getGroup(Integer id) {
		return	oauthTemplate.exchange(Endpoint.GROUP.getId(id), 
		                          HttpMethod.GET, null, Group.class);
	}
}
