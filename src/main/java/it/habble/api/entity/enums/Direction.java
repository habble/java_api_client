package it.habble.api.entity.enums;

import java.util.List;
import java.util.ResourceBundle;

public enum Direction {
	OUTGOING(1, "Uscente"),
	INCOMING(2,	"Entrante"),
	INTERNAL(4, "Locale"),
	TRANSIT(8, "Transito"),
	UNKNOWN(10, "Sconosciuta");
	
	private Integer id;
	private String label;
	
	Direction(Integer id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public Integer id() { return this.id; }
	
	public String label() { 
		return this.label; 
	}
	
	public String label(ResourceBundle bundle) {
		return bundle.getString("DirezioneChiamata." + this.label().trim().replace(" ", ""));
	}
	
	public static String label(ResourceBundle bundle, Integer id) {
		if(id == null)
			return "!DirectionNull!";
		
		for (Direction o : values()) 			
			if(o.id().equals(id))
				return bundle.getString("DirezioneChiamata." + o.label().trim().replace(" ", ""));

		return "!Direction-" + id + "!";
	}
	
	public static Boolean hasOutgoing(List<Integer> ids) {
		return ids != null && ids.contains(OUTGOING.id());
	}
	
	public static Boolean hasIncoming(List<Integer> ids) {
		return ids != null && ids.contains(OUTGOING.id());
	}
	
	public static Boolean hasInternal(List<Integer> ids) {
		return ids != null && ids.contains(INTERNAL.id());
	}
	
	public static Boolean hasTransit(List<Integer> ids) {
		return ids != null && ids.contains(TRANSIT.id());
	}
	
	public static Boolean hasUnknown(List<Integer> ids) {
		return ids != null && ids.contains(UNKNOWN.id());
	}
}
