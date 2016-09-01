package it.habble.api.entity.filter;

import java.util.LinkedList;

public class ClientFilterGroup extends CommonClientFilter {
	private LinkedList<ClientFilter> group;

	public LinkedList<ClientFilter> getGroup() {
		return this.group;
	}
	
	public ClientFilterGroup setOperator(Operator op) {
		this.operator = op;
		return this;
	}
	
	public ClientFilterGroup setGroup(LinkedList<ClientFilter> group) {
		this.group = group;
		return this;
	}
	
	public ClientFilterGroup addFilter(ClientFilter f) {
		if(group == null)
			group = new LinkedList<ClientFilter>();
		
		group.add(f);
		return this;
	}
}
