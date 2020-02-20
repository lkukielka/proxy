package com.flightsearch.proxy.controller;

import java.util.Date;
import java.util.List;
import com.flightsearch.proxy.model.flights.FlightSummary;
import com.flightsearch.proxy.model.flights.FlightsRequest;
import com.flightsearch.proxy.service.flights.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightsController {
	private final FlightsService flightsService;

	@Autowired
	public FlightsController(FlightsService flightsService) {
		this.flightsService = flightsService;
	}

	@GetMapping("")
	@ResponseBody
	public List<FlightSummary> getFlights(
			@RequestParam String origin,
			@RequestParam String destination,
			@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
			@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date returnDate,
			@RequestParam long minDuration,
			@RequestParam long maxDuration) {
		return this.flightsService.getFlightsSummary(new FlightsRequest(origin, destination, startDate, returnDate, minDuration, maxDuration));
	}
}
