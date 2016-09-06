package it.habble.api.request;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import it.habble.api.HabbleAPI;
import it.habble.api.endpoints.Endpoint;
import it.habble.api.entity.Call;
import it.habble.api.entity.CallTotals;
import it.habble.api.entity.ClientRequest;

public class CallRequest extends HabbleAPI {

	public CallRequest() throws Exception {
		super();
	}
	
	/**
	 * Makes a POST request by passing a custom filter
	 * @param request
	 * @return
	 */
	public ResponseEntity<List<Call>> callRequest(ClientRequest request) {
		return oauthTemplate.exchange(Endpoint.CALLS.toString(), 
			  HttpMethod.POST, new HttpEntity<ClientRequest>(request, getJsonHeaders()), 
			  new ParameterizedTypeReference<List<Call>>() {});
	}
	
	/**
	 * Makes a POST request by passing a custom filter
	 * @param request
	 * @return
	 */
	public ResponseEntity<List<CallTotals>> callTotalsRequest(ClientRequest request) {
		return oauthTemplate.exchange(Endpoint.CALLS_TOTALS.toString(), 
				  HttpMethod.POST, new HttpEntity<ClientRequest>(request, getJsonHeaders()), 
				  new ParameterizedTypeReference<List<CallTotals>>() {});
	}
}
