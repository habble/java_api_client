package it.habble.api.agent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Maps the message block in agent data submission
  <pre>
  {
	"localId": 149,
	"mcc": "222",
	"id": "203",
	"partner": "TIMCafe'",
	"sizeAmount": 93,
	"sizeUnit": 1,
	"type": 0,
	"direction": "0",
	"timestamp": "2016-03-18 11:19:23"
  }
  </pre>
 */
public class AgentMessagesData {
	public Integer localId;
	public String direction, status;
	public String partner, id;
	public Integer sizeAmount, sizeUnit, type;
	public String mcc;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="CET")
	public Date timestamp;
	
	public AgentMessagesData direction(String dir) {
		this.direction = dir;
		return this;
	}
	
	public AgentMessagesData type(Integer type) {
		this.type = type;
		return this;
	}
	
	public AgentMessagesData status(String status) {
		this.status = status;
		return this;
	}
	
	public AgentMessagesData localId(Integer id) {
		this.localId = id;
		return this;
	}
	
	public AgentMessagesData id(String id) {
		this.id = id;
		return this;
	}
	
	public AgentMessagesData partner(String partner) {
		this.partner = partner;
		return this;
	}
	
	public AgentMessagesData sizeAmount(Integer sizeAmount) {
		this.sizeAmount = sizeAmount;
		return this;
	}
	
	public AgentMessagesData sizeUnit(Integer unit) {
		this.sizeUnit = unit;
		return this;
	}
	
	public AgentMessagesData mcc(String mcc) {
		this.mcc = mcc;
		return this;
	} 
	
	/**
	 * Used by test, date conversion from string
	 * @param date
	 * @return
	 */
	public AgentMessagesData timestamp(String date) {
		try {
			timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AgentMessagesData timestamp(Date date) {
		this.timestamp = date;
		return this;
	}
}
