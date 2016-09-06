package it.habble.api.endpoints;

/**
 * List of available endpoints
 * @author ccastelli */
public enum Endpoint {
	
	ACTIVATION(baseHost() + "/agent/activation"),	// Habble apps' activation
	DATA_SUBMISSION(apiBaseUrl() + "/agent/data"),	// Habble apps' endpoint for data submission
	CALLS(apiBaseUrl() + "/calls"),					// calls' details (mobile and landline)
	CALLS_TOTALS(apiBaseUrl() + "/calls/totals"),	// calls grouped by costs, duration and total amount
	CONTACTS_LIST(apiBaseUrl() + "/contacts"),		// list of all customer's contacts
	CONTACT(apiBaseUrl() + "/contact"),				// Update, Insert
	DEPARTMENT(apiBaseUrl() + "/department"),		// Update, Insert
	DEPARTMENTS_LIST(apiBaseUrl() + "/departments"),// list of all customer's departments
	GROUPS_LIST(apiBaseUrl() + "/groups"),			// list of all customer's groups
	GROUP(apiBaseUrl() + "/group"),					// Update, Insert
	SITES_LIST(apiBaseUrl() + "/sites"),			// list of all customer's sites
	SITE(apiBaseUrl() + "/site");					// Update, Insert
		
	private String url;

	Endpoint(String url) {
		this.url = url;
	}

	public static String baseHost() { return "https://cdn.habble.it"; }
	public static String apiBaseUrl() { return baseHost() + "/api";   }
	public static String tokenUrl() { return apiBaseUrl() + "/token"; }
	
	/**
	 * For getting resources identified by an ID
	 * @param id
	 * @return
	 */
	public String getId(Integer id) {
		return this.url + "/" + id;
	}
	
	@Override
	public String toString() {
		return this.url;
	}
}
