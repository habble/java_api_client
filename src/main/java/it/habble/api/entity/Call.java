package it.habble.api.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({ "allowedFields" })
public class Call {

	private Integer id;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date date;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Date callStart;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Date callEnd;
	
	@JsonProperty("contact")
	private Contact contact;
	
	private Site site;
	
	private Group group;
	
	private Department department;
	
	private Integer outcome, direction, route;
	
	private Integer incomingTechnology, outcomingTechnology;
	
	private Integer deviceId;
	
	private Integer duration;
	
	private String internalNumber, externalNumber;
	
	private Double cost;
	
	private Integer callType;
	
	private Boolean roaming, ram;
	
	private String clusterName;
	
	private String partnerRegion;
	
	private String partnerLocation;
	
	private String provider;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getCallStart() {
		return this.callStart;
	}

	public void setCallStart(Date callStart) {
		this.callStart = callStart;
	}

	public Date getCallEnd() {
		return this.callEnd;
	}

	public void setCallEnd(Date callEnd) {
		this.callEnd = callEnd;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getOutcome() {
		return this.outcome;
	}

	public void setOutcome(Integer outcome) {
		this.outcome = outcome;
	}

	public Integer getDirection() {
		return this.direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getRoute() {
		return this.route;
	}

	public void setRoute(Integer route) {
		this.route = route;
	}

	public Integer getIncomingTechnology() {
		return this.incomingTechnology;
	}

	public void setIncomingTechnology(Integer incomingTechnology) {
		this.incomingTechnology = incomingTechnology;
	}

	public Integer getOutcomingTechnology() {
		return this.outcomingTechnology;
	}

	public void setOutcomingTechnology(Integer outcomingTechnology) {
		this.outcomingTechnology = outcomingTechnology;
	}

	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getInternalNumber() {
		return this.internalNumber;
	}

	public void setInternalNumber(String internalNumber) {
		this.internalNumber = internalNumber;
	}

	public String getExternalNumber() {
		return this.externalNumber;
	}

	public void setExternalNumber(String externalNumber) {
		this.externalNumber = externalNumber;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getCallType() {
		return this.callType;
	}

	public void setCallType(Integer callType) {
		this.callType = callType;
	}

	public Boolean getRoaming() {
		return this.roaming;
	}

	public void setRoaming(Boolean roaming) {
		this.roaming = roaming;
	}

	public Boolean getRam() {
		return this.ram;
	}

	public void setRam(Boolean ram) {
		this.ram = ram;
	}

	public String getClusterName() {
		return this.clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getPartnerRegion() {
		return this.partnerRegion;
	}

	public void setPartnerRegion(String partnerRegion) {
		this.partnerRegion = partnerRegion;
	}

	public String getPartnerLocation() {
		return this.partnerLocation;
	}

	public void setPartnerLocation(String partnerLocation) {
		this.partnerLocation = partnerLocation;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	/**
	 * Format duration as "mm:ss"
	 * @return
	 */
	public String formatDuration()
	{
		long seconds, minutes = 0;
		String results = "";

		if (duration != null)
		{
			seconds = duration % 60;
			minutes = ((duration - seconds) / 60);
			results = String.valueOf(minutes + ":" + seconds).toString();
			String[] split = results.split("\\:");
			String dimMinutes = split[0];
			String dimSeconds = split[1];

			if (dimMinutes.length() == 1 && dimSeconds.length() == 1) {
				results = String.valueOf("0" + minutes + ":" + "0" + seconds).toString();
			} else {
				if (dimMinutes.length() == 1) {
					results = String.valueOf("0" + minutes + ":" + seconds).toString();
				}
				
				if (dimSeconds.length() == 1) {
					results = String.valueOf(minutes + ":" + "0" + seconds).toString();
				}
			}
		}
		return results;
	}
}
