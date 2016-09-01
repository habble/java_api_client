package it.habble.api.entity.enums;

import java.util.List;
import java.util.ResourceBundle;

public enum Outcome {
	ANSWERED(1, "Risposta"),
	NOT_ANSWERED(2, "Non Risposta"),
	BUSY(4, "Occupata"),
	FAILED(8, "Fallita"),
	CONGESTED(16, "Dispositivo Congestionato");
	
	private Integer id;
	private String label;
	
	Outcome(Integer id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public Integer id() { return this.id; }
	
	public String label() { 
		return this.label; 
	}
	
	public String label(ResourceBundle bundle) {
		return bundle.getString("Esito." + this.label().trim().replace(" ", ""));
	}
	
	public static String label(ResourceBundle bundle, Integer id) {
		if(id == null)
			return "!OutcomeNull!";
		
		for (Outcome o : values()) 			
			if(o.id().equals(id))
				return bundle.getString("Esito." + o.label().trim().replace(" ", ""));

		return "!Outcome-" + id + "!";
	}
	
	public static Boolean hasAnswered(List<Integer> ids) {
		return ids != null && ids.contains(ANSWERED.id());
	}
	
	public static Boolean hasNotAnswered(List<Integer> ids) {
		return ids != null && ids.contains(NOT_ANSWERED.id());
	}
	
	public static Boolean hasBusy(List<Integer> ids) {
		return ids != null && ids.contains(BUSY.id());
	}
	
	public static Boolean hasFailed(List<Integer> ids) {
		return ids != null && ids.contains(FAILED.id());
	}
	
	public static Boolean hasCongested(List<Integer> ids) {
		return ids != null && ids.contains(CONGESTED.id());
	}
}
