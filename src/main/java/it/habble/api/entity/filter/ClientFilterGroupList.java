package it.habble.api.entity.filter;

import java.util.LinkedList;
 
public class ClientFilterGroupList extends CommonClientFilter {
	private LinkedList<ClientFilterGroup> list;
	
	public ClientFilterGroupList addGroup(ClientFilterGroup g) {
		if(list == null)
			list = new LinkedList<ClientFilterGroup>();
		
		list.add(g);
		return this;
	}

	public LinkedList<ClientFilterGroup> getList() {
		return this.list;
	}

	public ClientFilterGroupList setList(LinkedList<ClientFilterGroup> list) {
		this.list = list;
		return this;
	}
}
