package it.habble.api.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.habble.api.agent.security.Authorization;

/**
 * Object wrapper for activation response
 * @author ccastelli */
public class ActivationResponse {
	@JsonProperty(value = "authorization")
	private Authorization authorization;
	
	@JsonProperty(value = "configuration")
	private ActivationConfiguration configuration;
	
	public ActivationConfiguration getConfiguration() {
		return configuration;
	}

	public ActivationResponse setConfiguration(ActivationConfiguration configuration) {
		this.configuration = configuration;
		return this;
	}

	public Authorization getAuthorization() {
		return authorization;
	}

	public ActivationResponse setAuthorization(Authorization authorization) {
		this.authorization = authorization;
		return this;
	}
	
	public ActivationResponse() {}

	public ActivationResponse(String clientId, String clientSecret) {
		this.authorization = new Authorization();
		this.authorization.clientId = clientId;
		this.authorization.clientSecret = clientSecret;
	}
}
