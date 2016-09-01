package it.habble.api.agent;
 
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.habble.api.agent.security.Info;
  
/**
 * Object wrapping JSON data collected by apps
 * @author ccastelli  */
@JsonIgnoreProperties({"valid", "keepAlive", "emptyMessage"})
public class AgentData {
	public Info info;
	public List<AgentCallsData> calls;
	public List<AgentMessagesData> messages;
	public List<AgentTrafficData> traffic;
	public String flowId;
	
	public AgentData flowId(String id) {
		this.flowId = id;
		return this;
	}
	
	public AgentData addCall(AgentCallsData data) {
		if(calls == null) calls = new ArrayList<AgentCallsData>();
		calls.add(data);
		return this;
	}
	
	public AgentData addMessage(AgentMessagesData data) {
		if(messages == null) messages = new ArrayList<AgentMessagesData>();
		messages.add(data);
		return this;
	}
	
	public AgentData addTraffic(AgentTrafficData data) {
		if(traffic == null) traffic = new ArrayList<AgentTrafficData>();
		traffic.add(data);
		return this;
	}
	
	/**
	 * Checks if it's a keep alive message (useful for NOC). 
	 * It must have only a block INFO and a flow_id
	 * @return
	 */
	public Boolean isKeepAlive() {
		return calls == null && messages == null &&
				traffic == null && info != null && 
				  StringUtils.isNotBlank(flowId);
	}
	
	/**
	 * It must have all three blocks empty
	 * @return
	 */
	public Boolean isEmptyMessage() {
		return (calls != null && calls.isEmpty() && 
				  messages != null && messages.isEmpty() &&
				     traffic != null && traffic.isEmpty());		
	}
	
	/**
	 * Checks if it's a valid message. At least one of the three 
	 * block must have some data, info block and flow_id must be present.
	 * @return
	 */
	public Boolean isValid() {
		return ((calls != null && !calls.isEmpty()) || 
				 (messages != null && !messages.isEmpty()) || 
				   (traffic != null && !traffic.isEmpty())) && 
				info != null && 
				  StringUtils.isNotBlank(flowId);
	}
	
	public boolean hasCalls() { return calls != null && !calls.isEmpty(); }
	public boolean hasMessages() { return messages != null && !messages.isEmpty(); }
	public boolean hasTraffic() { return traffic != null && !traffic.isEmpty(); }
}
