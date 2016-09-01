package it.habble.api.entity.enums;

import java.util.ResourceBundle;

public enum Technology {
	GSM_UMTS(3, "GSM/UMTS"),
	POTS(0, "POTS"),
	PRI_ISDN(1, "PRI (ISDN)"),
	BRI(2, "BRI (ISDN)"),
	UNKNOWN(5, "UNKNOW"),
	DISA(6, "DISA"),
	VOIP_INTERSITE(7, "VoIP Intersede"),
	VOIP(4, "VoIP"),
	PRI_BYPASS(10, "PRI (bypass)");
	
	private Integer id;
	private String label;
	
	Technology(Integer id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public Integer id() { return this.id; }
	
	public String label() { 
		return this.label; 
	}
	
	public String label(ResourceBundle bundle) {
		return bundle.getString("Tecnologia." + this.label().trim().replace(" ", ""));
	}
	
	public static String label(ResourceBundle bundle, Integer id) {
		if(id == null)
			return "!TechnologyNull!";
		
		for (Technology o : values()) 			
			if(o.id().equals(id))
				return bundle.getString("Tecnologia." + o.label().trim().replace(" ", ""));

		return "!Technology-" + id + "!";
	}
}
