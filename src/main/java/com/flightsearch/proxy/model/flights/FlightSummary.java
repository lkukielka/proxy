package com.flightsearch.proxy.model.flights;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightSummary implements Comparable<FlightSummary> {
	private String origin;
	private String destination;
	private Date startDate;
	private Date returnDate;
	private Float price;
	private String firstWayAirline;
	private String returnWayAirline;
	private long duration;

	public FlightSummary(Flight firstWayFlight, Flight returnWayFlight) {
		this.origin = firstWayFlight.getOrigin();
		this.destination = firstWayFlight.getDestination();
		this.startDate = firstWayFlight.getDepartDate();
		this.returnDate = returnWayFlight.getDepartDate();
		this.price = Float.parseFloat(firstWayFlight.getValue()) + Float.parseFloat(returnWayFlight.getValue());
		this.firstWayAirline = firstWayFlight.getGate();
		this.returnWayAirline = returnWayFlight.getGate();
		this.duration = ChronoUnit.DAYS.between(firstWayFlight.getDepartDate().toInstant().atZone(ZoneId.systemDefault()),
				returnWayFlight.getDepartDate().toInstant().atZone(ZoneId.systemDefault()));
	}

	@Override
	public int compareTo(FlightSummary o) {
		return this.price.compareTo(o.getPrice());
	}
}
