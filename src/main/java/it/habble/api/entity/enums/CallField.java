package it.habble.api.entity.enums;

import it.habble.api.entity.Call;
import it.habble.api.entity.filter.CommonClientFilter;

/** Fields allowed to be used with {@link CommonClientFilter} 
 * for {@link Call} entity 
 * @author ccastelli */
public enum CallField implements ApiField {
	
	ID("id"),
	CONTACT_ID("contactId"), DEPARTMENT_ID("departmentId"), GROUP_ID("groupId"), SITE_ID("siteId"),
	CALL_START("callStart"), CALL_END("callEnd"), TYPE("callType"),
	TIME_SLOT("timeSlot"), DURATION("duration"),
	TECH_IN("techIn"), TECH_OUT("techOut"), COST("cost"), 
	INTERNAL_NUMBER("internalNumber"), EXTERNAL_NUMBER("externalNumber"),
	CLUSTER_NAME("clusterName"), RAM("ram"), ROAMING("roaming"),
	PARTNER_REGION("partnerRegion"),PARTNER_LOCATION("partnerLocation"),
	PROVIDER("provider"), DIRECTION("direction"), ROUTE("route"), OUTCOME("outcome");
	
	private String field;
	
	CallField(String field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return this.field;
	}
}
