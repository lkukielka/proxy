package com.flightsearch.proxy.model.flights;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}