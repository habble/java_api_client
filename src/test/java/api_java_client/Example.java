package api_java_client;

import static it.habble.api.entity.filter.ClientFilter.newDateFilter;
import static it.habble.api.entity.filter.ClientFilter.newIntFilter;
import static it.habble.api.entity.filter.ClientFilter.newNotNullFilter;
import static it.habble.api.entity.filter.ClientFilter.newTimeFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.habble.api.HabbleAPI;
import it.habble.api.entity.Call;
import it.habble.api.entity.CallTotals;
import it.habble.api.entity.ClientRequest;
import it.habble.api.entity.enums.CallField;
import it.habble.api.entity.enums.CallType;
import it.habble.api.entity.enums.Direction;
import it.habble.api.entity.filter.ClientFilterGroup;
import it.habble.api.entity.filter.Operator;
import it.habble.api.entity.filter.Paginator;

public class Example {

	public static void main(String[] args) {
		try {
			HabbleAPI api = new HabbleAPI();
			
			/* CALLS examples ------------------------------------------------ 
			 * Due to the complexity of the possible filter combinations, we use a set of filter classes 
			 * to combine filters. You can link filter groups with AND or OR operators.
			 * Calls requests always use POST method and have filters inside their body. */
			
			// 1. example: we want total costs, total duration and total calls of all incoming calls that 
			// started between 1st January and 7th January (ends included), whose duration was greater 
			// that 10 seconds
			ClientRequest request = new ClientRequest()
					.setFilters(
						new ClientFilterGroup().setOperator(Operator.AND)
							.addFilter(newDateFilter(CallField.CALL_START, "2016-04-01", Operator.GTE))
							.addFilter(newDateFilter(CallField.CALL_START, "2016-04-07", Operator.LTE))
							.addFilter(newIntFilter(CallField.DIRECTION, Direction.INCOMING.id(), Operator.EQUAL_TO))
							.addFilter(newIntFilter(CallField.DURATION, 10, Operator.GT))
					);
			
			System.out.println("POST body: " + api.toJson(request));
			
			List<CallTotals> totals = api.calls().callTotalsRequest(request).getBody();
			System.out.println(totals.size() + " totals by currency");
			
			
			// 2. example: we want all landline calls which started between 1st January and 7th January (ends included),
			// in the hour range from 12am and 14pm, where partner location is known
			ClientFilterGroup filters = 
					 new ClientFilterGroup()
					 	.addFilter(newIntFilter(CallField.TYPE, CallType.LANDLINE.id(), Operator.EQUAL_TO))
					 	.setOperator(Operator.AND);
		
			Map<String, String> slots = new HashMap<String, String>();
			slots.put("12:00", "14:00");
				
			filters.addFilter(newDateFilter(CallField.CALL_START, "2016-04-01", Operator.GTE))
				   .addFilter(newDateFilter(CallField.CALL_END, "2016-04-07", Operator.LTE))
				   .addFilter(newTimeFilter(CallField.TIME_SLOT, slots))
				   .addFilter(newNotNullFilter(CallField.PARTNER_LOCATION)) ;

			List<Call> calls = api.calls().callRequest(request).getBody();			 
			System.out.println(calls.size() + " calls");
			
			
			/* CONTACTS -------------------------------------------------- */
			Paginator paginator = new Paginator()
									.orderBy("surname", "DESC")
									.offset(2)
									.limit(1);

			api.contacts().getContacts(paginator);
			 
		} catch(Exception e) {
			String cause = e.getCause() != null ? e.getCause() + ": " : "";
			System.err.println(cause + e.getMessage());
		}
	}
}
