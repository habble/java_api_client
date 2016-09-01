package it.habble.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"@id"})
public class Site {
	private Integer id;
	private String name;
	private String extraInfo;
	private String siteCurrency;
	private Group group;
	
	public Site() {}
	
	public Site(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public Site setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public Site setName(String name) {
		this.name = name;
		return this;
	}

	public String getExtraInfo() {
		return this.extraInfo;
	}

	public Site setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
		return this;
	}

	public String getSiteCurrency() {
		return this.siteCurrency;
	}

	public Site setSiteCurrency(String siteCurrency) {
		this.siteCurrency = siteCurrency;
		return this;
	}

	public Group getGroup() {
		return this.group;
	}

	public Site setGroup(Group group) {
		this.group = group;
		return this;
	} 
	
	@Override
	public Site clone() {
		return new Site(this.id)
					.setName(this.name)
					.setGroup(this.group)
					.setSiteCurrency(this.siteCurrency)
					.setExtraInfo(this.extraInfo);
	}
}
