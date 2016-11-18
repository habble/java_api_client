package it.habble.api.agent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Maps the call block in agent data submission
  <pre>
  {
  	"direction": "2",
    "id": "216",
    "status": "1",
    "calledNumber": "",
    "callerNumber": " 390854151424",
    "grossDuration": 49,
    "mcc": "222",
    "netDuration": 49,
    "answer": "2016-03-22 15:45:04",
    "end": "2016-03-22 15:45:53",
    "start": "2016-03-22 15:45:04"
  }
 * @author ccastelli */
public class AgentCallsData {
	public String direction, status, id;
	public String calledNumber, callerNumber;
	public Integer grossDuration, netDuration;
	public String mcc;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="CET")
	public Date answer, end, start;
	
	public AgentCallsData direction(String d) {
		this.direction = d;
		return this;
	}
	
	public AgentCallsData status(String status) {
		this.status = status;
		return this;
	}
	
	public AgentCallsData id(String id) {
		this.id = id;
		return this;
	}
	
	public AgentCallsData calledNumber(String num) {
		this.calledNumber = num;
		return this;
	}
	
	public AgentCallsData callerNumber(String num) {
		this.callerNumber = num;
		return this;		
	}
	
	public AgentCallsData grossDuration(Integer dur) {
		this.grossDuration = dur;
		return this;
	}
	
	public AgentCallsData netDuration(Integer dur) {
		this.netDuration = dur;
		return this;
	}
	
	public AgentCallsData mcc(String mcc) {
		this.mcc = mcc;
		return this;
	} 
	
	/**
	 * Used by test, date conversion from string
	 * @param date
	 * @return
	 */
	public AgentCallsData answer(String date) {
		try {
			this.answer = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AgentCallsData answer(Date date) {
		this.answer = date;
		return this;
	}
	
	/**
	 * Used by test, date conversion from string
	 * @param date
	 * @return
	 */
	public AgentCallsData end(String date) {
		try {
			this.end = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AgentCallsData end(Date date) {
		this.end = date;
		return this;
	}
	
	/**
	 * Used by test, date conversion from string
	 * @param date
	 * @return
	 */
	public AgentCallsData start(String date) {
		try {
			this.start = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AgentCallsData start(Date date) {
		this.start = date;
		return this;
	}
}
