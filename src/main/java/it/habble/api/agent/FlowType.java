package it.habble.api.agent;

import java.util.Locale;


public enum FlowType {
	CONNECTIONS, MESSAGES, CALLS, // OLD
	/** it has all block empty or null */
	KEEP_ALIVE, 
	/** Used only when it refers to the whole flow */
	DATA, /** calls, messages and traffic data */
	ACTIVATION;
	
	/**
	 * Conversion from string to its relative {@link FlowType}
	 * @param flow a string representation of a {@link FlowType}
	 * @return
	 */
	public static FlowType getFlow(String flow) {
		if(flow != null && !flow.isEmpty())
			return FlowType.valueOf(flow.toUpperCase(Locale.ENGLISH));
		else
			throw new IllegalArgumentException("Cannot instantiate FlowType for the flow specified: empty or null");
	}
}
