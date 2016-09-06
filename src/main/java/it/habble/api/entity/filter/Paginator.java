package it.habble.api.entity.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Wrapper for query string parameters for paginating the results
 * @author ccastelli
 *
 */
public class Paginator {
	private String orderBy, orderDir;
	private Integer offset, limit;

	public Paginator orderBy(String field, String direction) {
		orderBy = field;
		
		if(StringUtils.isNotBlank(direction) || 
			 (!direction.equalsIgnoreCase("asc") || 
					!direction.equalsIgnoreCase("desc")))
			direction = "ASC";
		
		orderDir = direction;
		return this;
	}
	
	public Paginator orderBy(String field) {
		return orderBy(field, "ASC");
	}
	
	public Paginator offset(Integer offset) {
		this.offset = offset;
		return this;
	}
	
	public Paginator limit(Integer limit) {
		this.limit = limit;
		return this;
	}
	
	public String build(String url) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		
		if(StringUtils.isNotBlank(orderBy))
			builder.queryParam("orderBy", orderBy);
		
		if(StringUtils.isNotBlank(orderDir))
			builder.queryParam("orderDir", orderDir);
		
		if(offset != null)
			builder.queryParam("offset", offset);		
		
		if(limit != null)
			builder.queryParam("limit", limit);	

		return builder.toUriString();
	}

}
