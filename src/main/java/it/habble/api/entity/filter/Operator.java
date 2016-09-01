package it.habble.api.entity.filter;

/**
 * SQL operators
 * @author ccastelli */
public enum Operator {
	/** Usable with groups */ AND,
	/** Usable with groups */ OR,
	/** Usable only with lists */ IN, 
	/** Usable only with lists */ NOT_IN, 
	/** Usable only with strings */ STARTS_WITH, 
	/** Usable only with strings */ NOT_STARTS_WITH, 
	/** Usable only with strings */ ENDS_WITH,
	/** Usable only with strings */ NOT_ENDS_WITH,
	/** Usable only with lists */ CONTAINS, 
	/** Usable only with lists */ NOT_CONTAINS, EQUAL_TO, 
	/** Greater than */ GT, 
	/** Lower than */ LT, 
	/** Greater than or equal */ GTE, 
	/** Lower than or equal */ LTE,
	IS_NULL, NOT_NULL, 
	/** Requires a Map as value */ BETWEEN, 
	/** Code generated by server */ MAP;
	
	public Boolean isSkippable() {
		switch(this) {
			case MAP: return true;
			default: return false;
		}
	}
	
	public Boolean isGroupOp() {
		return this.toString().equals("AND") || 
				this.toString().equals("OR");
	}
	
	public Boolean isNullOp() {
		return this.toString().equals("IS_NULL") || 
				this.toString().equals("NOT_NULL");
	}
	
	public Boolean isListOp() {
		return this.toString().equals("IN") || 
				this.toString().equals("NOT_IN");
	}
	
	public Boolean isLikeOp() {
		return this.toString().equals("STARTS_WITH") || 
				 this.toString().equals("NOT_STARTS_WITH") || 
				 	this.toString().equals("CONTAINS") || 
				 	  this.toString().equals("NOT_CONTAINS") ||
				    this.toString().equals("NOT_ENDS_WITH") ||
				 this.toString().equals("ENDS_WITH");
	}
	
	public String getSql() throws Exception {
		switch(this) {
			case IN: return " IN ";
			case NOT_IN: return " NOT IN ";
			case STARTS_WITH: case CONTAINS: case ENDS_WITH: return " NOT ILIKE ?";	
			case NOT_STARTS_WITH: case NOT_CONTAINS: case NOT_ENDS_WITH: return " ILIKE ?";
			case EQUAL_TO: return " = ? ";
			case GT: return " > ?";
			case LT: return " < ?";
			case GTE: return " >= ?";
			case LTE: return " <= ?";
			case IS_NULL: return " IS NULL";
			case NOT_NULL: return " IS NOT NULL";
			case BETWEEN: return " BETWEEN ? AND ? ";
			case MAP: return "";
			default: throw new Exception("Unrecognized operator: " + this.toString());
		}
	}
}
