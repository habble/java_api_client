package it.habble.api.entity.filter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Parent class for groups of filters
 * @author ccastelli */
@JsonIgnoreProperties({ "parameters" })
public abstract class CommonClientFilter {
	/** Represents a valid SQL operator */
	protected Operator operator;
	
	public Operator getOperator() {
		return this.operator;
	}

	@SuppressWarnings("rawtypes")
	public void join(StringBuffer buf, List list) throws Exception {
		if(getOperator() == null)
			throw new Exception("Invalid JSON, You've missed an operator for a group?");
		
		buf.append(" ").append(getOperator().name()).append(" ");
	}

	/**
	 * Joins a list of groups
	 * @param buf current query
	 * @param list is a list of filters
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void join(StringBuffer buf, List list, Integer currentElement) throws Exception {
		if(currentElement < list.size()) {
			if(getOperator() == null || !getOperator().isGroupOp())
				throw new Exception("You must specify a AND/OR operator type for joining groups");
		
			buf.append(" ").append(getOperator().name()).append(" ");
		}
	}
}
