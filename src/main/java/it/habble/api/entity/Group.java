package it.habble.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"@id"})
public class Group {
	private Integer id;
	private String name;
	private String extraInfo;
	
	public String getExtraInfo() {
		return this.extraInfo;
	}

	public Group setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
		return this;
	}

	public Group() { }
	public Group(Integer id) { this.id = id; }

	public Integer getId() {
		return this.id;
	}

	public Group setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public Group setName(String name) {
		this.name = name;
		return this;
	}
	
	@Override
	public Group clone() {
		return new Group(this.id)
					.setName(this.name)
					.setExtraInfo(this.extraInfo);
	}
}
