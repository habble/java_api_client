package it.habble.api.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Additional info useful for mobile apps for remote configuration
 * @author ccastelli
 *
 */
public class ActivationConfiguration {
	
	@JsonProperty(value = "tokenEndpoint")
	private String tokenEndpoint;
	
	@JsonProperty(value = "phoneNumber")
	private String phoneNumber;

	public String getTokenEndpoint() {
		return tokenEndpoint;
	}

	public ActivationConfiguration setTokenEndpoint(String tokenEndpoint) {
		this.tokenEndpoint = tokenEndpoint;
		return this;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public ActivationConfiguration setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
}
