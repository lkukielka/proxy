package com.flightsearch.proxy.controller;

import com.flightsearch.proxy.model.flights.FlightSummary;
import com.flightsearch.proxy.model.flights.FlightsRequest;
import com.flightsearch.proxy.model.flights.FlightsSearchCriteria;
import com.flightsearch.proxy.service.flights.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
			FlightsSearchCriteria criteria) {
		return this.flightsService.getFlightsSummary(new FlightsRequest(criteria));
	}
}
