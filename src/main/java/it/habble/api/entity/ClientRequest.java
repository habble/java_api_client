package it.habble.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.habble.api.entity.enums.Order;
import it.habble.api.entity.filter.ClientFilter;
import it.habble.api.entity.filter.ClientFilterGroup;
import it.habble.api.entity.filter.ClientFilterGroupList;

/**
 * Object representation of the JSON request for querying calls web service
 * @author ccastelli */
@JsonIgnoreProperties({ "direction" })
public class ClientRequest {

	/** List of conditions to filter calls' result set */
	private ClientFilterGroupList filters;
	/** For limiting output's records */
	private Integer limit;
	/** Initial offset for limiting output's records */
	private Integer offset;
	/** the field's name to order result set */
	private String orderBy;
	/** For odering the result set */
	private Order orderDirection;
	
	public ClientRequest() {}
	
	public ClientRequest(ClientFilterGroupList filters) {
		this.filters = filters;
	}

	public ClientFilterGroupList getFilters() {
		return this.filters;
	}

	public ClientRequest setFilters(ClientFilterGroupList filters) {
		this.filters = filters;
		return this;
	}

	public ClientRequest setFilters(ClientFilterGroup filters) {
		this.filters = new ClientFilterGroupList().addGroup(filters);
		return this;
	}

	public ClientRequest setFilters(ClientFilter f) {
		this.filters = new ClientFilterGroupList()
							.addGroup(new ClientFilterGroup()
										.addFilter(f));
		return this;
	}

	public Integer getLimit() {
		return this.limit;
	}

	public ClientRequest setLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public Integer getOffset() {
		return this.offset;
	}

	public ClientRequest setOffset(Integer offset) {
		this.offset = offset;
		return this;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public ClientRequest setOrderBy(String orderBy) {
		this.orderBy = orderBy;
		return this;
	}

	public Order getOrderDirection() {
		return this.orderDirection;
	}

	public ClientRequest setOrderDirection(Order orderDirection) {
		this.orderDirection = orderDirection;
		return this;
	}	
	
	public String getDirection() {
		return (this.orderDirection != null)
			   ? this.orderDirection.name() : "";
	}
}
