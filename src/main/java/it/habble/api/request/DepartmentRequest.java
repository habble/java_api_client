package it.habble.api.request;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import it.habble.api.HabbleAPI;
import it.habble.api.endpoints.Endpoint;
import it.habble.api.entity.Department;
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
	
	public ResponseEntity<Department> getDepartment(Integer id) {
		return	oauthTemplate.exchange(Endpoint.DEPARTMENT.getId(id), 
		                          HttpMethod.GET, null, Department.class);
	}
}
