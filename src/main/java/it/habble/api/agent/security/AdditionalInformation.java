package it.habble.api.agent.security;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Additional information about a REST client.
 * It's used inside Interceptor class (EJB project).
 * @author ccastelli
 *
 */
public class AdditionalInformation {
	/** "test" used just for test */
	public String category;
	/** Foreign key to clienti table */
	public Integer idCustomer;
	/** Just for Habble User Agent */
	public String serial;
	/** if these credentials are enabled */
	public Boolean enabled;
	
	/** Creation's date of this client */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
	public Date createdOn;

	public AdditionalInformation category(String cat) {
		this.category = cat;
		return this;
	}
	
	public AdditionalInformation serial(String serial) {
		this.serial = serial;
		return this;
	}

	public AdditionalInformation customer(Integer id) {
		this.idCustomer = id;
		return this;
	}
	
	public AdditionalInformation enabled(Boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public AdditionalInformation created() {
		this.createdOn = new Date();
		return this;
	}
}
