package it.habble.api.entity.enums;

public enum CallType {
	MOBILE(1),
	LANDLINE(2);
	
	private Integer id;
	
	CallType(Integer id) {
		this.id = id;
	}
	
	public Integer id() { return this.id; }
	
	public static Boolean isMobile(Integer id) {
		return id != null && id.equals(MOBILE.id());
	}
	
	public static Boolean isLandline(Integer id) {
		return id != null && id.equals(LANDLINE.id());
	}
}
