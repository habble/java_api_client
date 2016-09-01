package it.habble.api.entity.enums;

import java.util.List;
import java.util.ResourceBundle;

public enum Route {
	INT_UNKNOWN(-2, "Internazionale Sconosciuto"),
	INTERNAL(0, "Locale"),
	NAT_LANDLINE(1, "Nazionale Fissi"),
	NAT_MOBILE(2, "Nazionale Mobili"),
	NAT_SPECIAL(3, "Nazionale Speciali"),
	INT_LANDLINE(4, "Internazionale Fissi"),
	INT_MOBILE(5, "Internazionale Mobili"),
	INT_SPECIAL(6, "Internazionale Speciali"),
	UNKNOWN(-1, "Sconosciuta"),
	URBAN(7, "Urbane");
	
	private Integer id;
	private String label;
	
	Route(Integer id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public Integer id() { return this.id; }
	
	public String label() { 
		return this.label; 
	}
	
	public String label(ResourceBundle bundle) {
		return bundle.getString("DirettriceChiamata." + this.label().trim().replace(" ", ""));
	}
	
	public static String label(ResourceBundle bundle, Integer id) {
		if(id == null)
			return "!RouteNull!";
		
		for (Route o : values()) 			
			if(o.id().equals(id))
				return bundle.getString("DirettriceChiamata." + o.label().trim().replace(" ", ""));

		return "!Route-" + id + "!";
	}
	
	public static Boolean hasInternationalUnknown(List<Integer> ids) {
		return ids != null && ids.contains(INT_UNKNOWN.id());
	}
	
	public static Boolean hasInternationalLandline(List<Integer> ids) {
		return ids != null && ids.contains(INT_LANDLINE.id());
	}
	
	public static Boolean hasInternationalMobile(List<Integer> ids) {
		return ids != null && ids.contains(INT_MOBILE.id());
	}
	
	public static Boolean hasInternationalSpecial(List<Integer> ids) {
		return ids != null && ids.contains(INT_SPECIAL.id());
	}
	
	public static Boolean hasInternal(List<Integer> ids) {
		return ids != null && ids.contains(INTERNAL.id());
	}
	
	public static Boolean hasUnknown(List<Integer> ids) {
		return ids != null && ids.contains(UNKNOWN.id());
	}
	
	public static Boolean hasUrban(List<Integer> ids) {
		return ids != null && ids.contains(URBAN.id());
	}
	
	public static Boolean hasNationalLandline(List<Integer> ids) {
		return ids != null && ids.contains(NAT_LANDLINE.id());
	}
	
	public static Boolean hasNationalMobile(List<Integer> ids) {
		return ids != null && ids.contains(NAT_MOBILE.id());
	}	
	
	public static Boolean hasNationalSpecial(List<Integer> ids) {
		return ids != null && ids.contains(NAT_SPECIAL.id());
	}
}
