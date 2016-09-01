package it.habble.api.entity.filter;
 
import java.text.SimpleDateFormat;
import java.util.Date;

import it.habble.api.error.HabbleException;

public enum FilterType {
	INTEGER, NUMERIC, STRING, BOOLEAN, DATE, TIMESTAMP, TIME, NULL;
	
	private String timeFormat = "HH:mm";
	private String dateFormat = "yyyy-MM-dd";
	private String timeStampFormat = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * @return TRUE if this enum is of type DATE, TIMESTAMP and TIME, FALSE
	 */
	public Boolean isDate() {
		switch(this) {
			case DATE: case TIMESTAMP: case TIME: return true;
			default: return false;
		}
	}
	
	public Boolean isTime() {
		return this.equals(TIME);
	}
	
	public Date toDate(String date) throws HabbleException {
		try {
			switch(this) {
				case DATE: return new SimpleDateFormat(dateFormat).parse(date);
				case TIMESTAMP: return new SimpleDateFormat(timeStampFormat).parse(date);
				case TIME: return new SimpleDateFormat(timeFormat).parse(date);
				default: throw new Exception("You can only convert DATE, TIMESTAMP and TIME types");
			}
		} catch(Exception e) {
			throw new HabbleException("Invalid JSON", "Cannot parse date: " + date);
		}
	}

	public String getTimeFormat() {
		return this.timeFormat;
	}

	public String getDateFormat() {
		return this.dateFormat;
	}

	public String getTimeStampFormat() {
		return this.timeStampFormat;
	}
}
