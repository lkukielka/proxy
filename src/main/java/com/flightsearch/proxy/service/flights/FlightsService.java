package com.flightsearch.proxy.service.flights;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.flightsearch.proxy.model.flights.Flight;
import com.flightsearch.proxy.model.flights.FlightResponse;
import com.flightsearch.proxy.model.flights.FlightSummary;
import com.flightsearch.proxy.model.flights.FlightsRequest;
import com.flightsearch.proxy.service.PropertiesService;
import com.flightsearch.proxy.service.RestClientService;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightsService {
	private final PropertiesService properties;
	private final RestClientService restClient;

	@Autowired
	public FlightsService(PropertiesService properties, RestClientService restClient) {
		this.properties = properties;
		this.restClient = restClient;
	}

	public List<FlightSummary> getFlightsSummary(FlightsRequest flightsRequest) {
		return getPossibleConnections(getFirstWayFlights(flightsRequest), getReturnFlights(flightsRequest), flightsRequest);
	}

	private List<Flight> getFirstWayFlights(FlightsRequest flightsRequest) {
		return getFlights(flightsRequest.getOrigin(), flightsRequest.getDestination(), flightsRequest.getStartDate());
	}

	private List<Flight> getReturnFlights(FlightsRequest flightsRequest) {
		return getFlights(flightsRequest.getDestination(), flightsRequest.getOrigin(), flightsRequest.getStartDate());
	}

	private List<FlightSummary> getPossibleConnections(List<Flight> firstWayFlight, List<Flight> returnWayFlights, FlightsRequest request) {
		return firstWayFlight.stream()
				.flatMap(firstWay ->
					returnWayFlights.stream()
							.map(returnWay -> new FlightSummary(firstWay, returnWay)
			)).collect(Collectors.toList()).stream()
				.filter(summary -> summary.getDuration() >= request.getMinDuration() && summary.getDuration() <= request.getMaxDuration())
				.sorted()
				.collect(Collectors.toList());
	}

	private List<Flight> getFlights(String origin, String destination, Date month) {
		return Try.of(() -> this.restClient.get(this.properties.getTravelpayoutsFlightsUrl()
						.replaceAll(":origin", origin)
						.replaceAll(":destination", destination)
						.replaceAll(":month", formatDate(month)),
				FlightResponse.class)).get().getData();
	}

	private String formatDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
}
