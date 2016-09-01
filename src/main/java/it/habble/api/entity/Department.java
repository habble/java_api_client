package it.habble.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"@id"})
public class Department {
	private Integer id;
	private String name;
	private String extraInfo;
	private Site site;
	
	public Department() {}
	
	public Department(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public Department setId(Integer id) {
		this.id = id;
		return this;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Department setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getExtraInfo() {
		return this.extraInfo;
	}
	
	public Department setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
		return this;
	}
	
	public Site getSite() {
		return this.site;
	}
	
	public Department setSite(Site site) {
		this.site = site;
		return this;
	}
	
	@Override
	public Department clone() {
		return new Department(this.id)
					.setName(this.name)
					.setSite(this.site)
					.setExtraInfo(this.extraInfo);
	}
}
