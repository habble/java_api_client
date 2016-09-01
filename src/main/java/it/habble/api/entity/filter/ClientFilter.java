package it.habble.api.entity.filter;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import it.habble.api.entity.enums.ApiField;

/**
 * @author ccastelli */
public class ClientFilter extends CommonClientFilter {

	private String field;
	private Object value;
	private FilterType type;
	
	public ClientFilter() {}
	
	/**
	 * Preliminary checks for specified filters
	 * @return
	 * @throws Exception
	 */
	public Boolean validate() throws Exception {
		if(type == null) 
			throw new Exception("Please specify type for filter " + this.field);
		
		if(StringUtils.isBlank(this.field)) 
			throw new Exception("Please specify fields for all your filters");
		
		Boolean finalResult = false;
		
		if(!this.operator.isNullOp()) {
			switch(type) {
				case BOOLEAN: finalResult = this.value instanceof Boolean; break;
				case INTEGER: finalResult = this.value instanceof Integer; break;
				case DATE: case TIMESTAMP: finalResult = this.value instanceof String ||
															this.value instanceof Date; break;
				case NUMERIC: finalResult = this.value instanceof Double || 
						   this.value instanceof Float; break;
				case STRING: finalResult = this.value instanceof String; break;
				case TIME: finalResult = this.value instanceof Map; break;
				default: throw new Exception("Unknown type");
			}
			
			if(!finalResult)
				throw new Exception("Value has different type respect the one specified");
		}
		
		return true;
	}

	public ClientFilter setOperator(Operator op) {	
		this.operator = op;
		return this;
	}
	
	private static ClientFilter newFilter(ApiField field, Object value, Operator op) {	
		return new ClientFilter()
				.setField(field != null ? field.toString() : null)
				.setValue(value)
				.setOperator(op);
	}
	
	public static ClientFilter newIntFilter(ApiField field, Number value, Operator op) throws Exception {
		ClientFilter f = newFilter(field, value, op).setType(FilterType.INTEGER);
		return (f.validate()) ? f : null;
	}
	
	public static ClientFilter newNumericFilter(ApiField field, Number value, Operator op) throws Exception {
		ClientFilter f = newFilter(field, value, op).setType(FilterType.NUMERIC);
		return (f.validate()) ? f : null;
	}
	
	public static ClientFilter newDateFilter(ApiField field, String value, Operator op) throws Exception {
		ClientFilter f = newFilter(field, value, op).setType(FilterType.DATE);
		return (f.validate()) ? f : null;
	}
	
	public static ClientFilter newDateFilter(ApiField field, Date value, Operator op) throws Exception {
		
		if(value == null)
			throw new Exception("Null date for filter " + field);
		
		String date = FastDateFormat.getInstance(FilterType.DATE.getDateFormat()).format(value);
		
		ClientFilter f = newFilter(field, date, op).setType(FilterType.DATE);
		return (f.validate()) ? f : null;
	}
	
	public static ClientFilter newTimestampFilter(ApiField field, String value, Operator op) throws Exception {
		ClientFilter f = newFilter(field, value, op).setType(FilterType.TIMESTAMP);
		return (f.validate()) ? f : null;
	}
	
	public static ClientFilter newTimestampFilter(ApiField field, Date value, Operator op) throws Exception {
		
		if(value == null)
			throw new Exception("Null date for filter " + field);
		
		String date = FastDateFormat.getInstance(FilterType.TIMESTAMP.getDateFormat()).format(value);
		
		ClientFilter f = newFilter(field, date, op).setType(FilterType.TIMESTAMP);
		return (f.validate()) ? f : null;
	}
	
	public static ClientFilter newTimeFilter(ApiField field, Map<String, String> value) throws Exception {
		ClientFilter f = newFilter(field, value, Operator.MAP).setType(FilterType.TIME);
		return (f.validate()) ? f : null;
	}
	
	public static ClientFilter newStringFilter(ApiField field, String value, Operator op) throws Exception {
		ClientFilter f = newFilter(field, value, op).setType(FilterType.STRING);
		return (f.validate()) ? f : null;
	}
	
	public static ClientFilter newBoolFilter(ApiField field, Boolean value, Operator op) throws Exception {
		ClientFilter f = newFilter(field, value, op).setType(FilterType.BOOLEAN);
		return (f.validate()) ? f : null;
	}
	
	public static ClientFilter newMapFilter(ApiField field, Map<String, String> map, FilterType type) throws Exception {
		ClientFilter f = newFilter(field, map, Operator.MAP).setType(type);
		return (f.validate()) ? f : null;
	}
	
	public static ClientFilter newNotNullFilter(ApiField field) throws Exception {
		ClientFilter f = newNullFilter(field, Operator.NOT_NULL);
		return (f.validate()) ? f : null;
	}
	
	public static ClientFilter newIsNullFilter(ApiField field) throws Exception {
		ClientFilter f = newNullFilter(field, Operator.IS_NULL);
		return (f.validate()) ? f : null;
	}
	
	private static ClientFilter newNullFilter(ApiField field, Operator op) throws Exception {
		ClientFilter f = newFilter(field, null, op).setType(FilterType.NULL);
		return (f.validate()) ? f : null;
	}

	public String getField() {
		return this.field;
	}

	private ClientFilter setField(String field) {
		this.field = field;
		return this;
	}

	public Object getValue() {
		return this.value;
	}

	private ClientFilter setValue(Object value) {
		this.value = value;
		return this;
	}

	public FilterType getType() {
		return this.type;
	}

	private ClientFilter setType(FilterType type) {
		this.type = type;
		return this;
	}
}
