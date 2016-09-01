package it.habble.api.agent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Maps the traffic block in agent data submission
  <pre>
  { 
    "date": "2016-03-22",
    "id_send": 832,
    "id_send_date": 1458650925697,
    "mcc": "222",
    "mnc": "01",
    "sizeUnit": "2",
    "amount": 2833064        
  }
  </pre>
 * @author ccastelli */
public class AgentTrafficData {
	public Integer idSend;
	public String idSendDate;
	public Integer amount, sizeUnit;
	public String mcc, mnc;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="CET")
	public Date date;
	
	public AgentTrafficData idSend(Integer id) {
		idSend = id;
		return this;
	}
	
	public AgentTrafficData idSendDate(String id) {
		idSendDate = id;
		return this;
	}
	
	public AgentTrafficData amount(Integer amount) {
		this.amount = amount;
		return this;
	}
	
	public AgentTrafficData sizeUnit(Integer sizeUnit) {
		this.sizeUnit = sizeUnit;
		return this;
	}
	
	public AgentTrafficData mcc(String mcc) {
		this.mcc = mcc;
		return this;
	} 
	
	public AgentTrafficData mnc(String mnc) {
		this.mnc = mnc;
		return this;
	} 
	
	/**
	 * Used by test, date conversion from string
	 * @param date
	 * @return
	 */
	public AgentTrafficData date(String date) {
		try {
			this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AgentTrafficData date(Date date) {
		this.date = date;
		return this;
	}
}
