package it.habble.api.entity;

/**
 * Maps the query for retrieving totals for a certain period
 * @author ccastelli */
public class CallTotals {
	
	private Double cost;
	private Integer duration;
	private String currency;
	private Integer calls;
	
	public CallTotals() {}

	public Double getCost() {
		return this.cost;
	}

	public CallTotals setCost(Double cost) {
		this.cost = cost;
		return this;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public CallTotals setDuration(Integer duration) {
		this.duration = duration;
		return this;
	}

	public String getCurrency() {
		return this.currency;
	}

	public CallTotals setCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public Integer getCalls() {
		return this.calls;
	}

	public CallTotals setCalls(Integer calls) {
		this.calls = calls;
		return this;
	}
}
