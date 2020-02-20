package com.flightsearch.proxy.model.flights;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightsRequest {
	private String origin;
	private String destination;
	private Date startDate;
	private Date returnDate;
	private long minDuration;
	private long maxDuration;

	public FlightsRequest(FlightsSearchCriteria criteria) {
		this.origin = criteria.getOrigin();
		this.destination = criteria.getDestination();
		this.startDate = criteria.getStartDate();
		this.returnDate = criteria.getReturnDate();
		this.minDuration = criteria.getMinDuration();
		this.maxDuration = criteria.getMaxDuration();
	}
}