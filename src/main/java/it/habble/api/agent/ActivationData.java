package it.habble.api.agent;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Used by /agent/activation
 * @author ccastelli */
@JsonIgnoreProperties({"valid"})
public class ActivationData {
	/** usually it's the user's email */
	public String activationCode;
	/** phone number for identifing the smartphone */ 
	public String phoneNumber;
	/** IMEI or other serial codes */
	public String serial; 
	/** ISO ALPHA-2 CODE two letters */
	public String isoCode; 
	
	/**
	 * We don't check <code>phone_number</code> validity for legacy support.
	 * @return
	 */
	public Boolean isValid() {
		return StringUtils.isNotBlank(activationCode) && 
						StringUtils.isNotBlank(serial);
	}
}
