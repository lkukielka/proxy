package com.flightsearch.proxy.service.gdansk;

import com.flightsearch.proxy.model.flights.Flight;
import com.flightsearch.proxy.model.flights.FlightsRequest;
import com.flightsearch.proxy.model.gdansk.FlightGdansk;
import com.flightsearch.proxy.model.gdansk.FlightGdanskResponse;
import com.flightsearch.proxy.service.IataService;
import com.flightsearch.proxy.service.PropertiesService;
import com.flightsearch.proxy.service.RestClientService;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

@Service
public class FlightsFromGdanskService {
    private final PropertiesService properties;
    private final RestClientService restClient;
    private final IataService iataService;

    @Autowired
    public FlightsFromGdanskService(IataService airportIata, RestClientService restClient, PropertiesService properties) {
        this.iataService = airportIata;
        this.properties = properties;
        this.restClient = restClient;
    }

    public FlightGdanskResponse getFlightsFromGdansk(String destination, String departDate, String returnDate) {
        return Try.of(() ->this.restClient.get(this.properties.getTravelpayoutsUrlGdansk()
                .replaceAll(":destination", destination)
                .replaceAll(":departDate", departDate)
                .replaceAll(":returnDate", returnDate),
                FlightGdanskResponse.class
            )).get();
    }

    public Map<String, FlightGdansk> getCheapestFlightsFromGdansk(String destination, String departDate, String returnDate) {
        Map<String, Map<String, FlightGdansk>> allFlightsWithNames =
                this.retrieveNames(this.getFlightsFromGdansk(destination, departDate, returnDate));
        Map<String, Map<String, FlightGdansk>> allFlightsSorted = allFlightsWithNames.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().entrySet().stream()
                            .sorted(comparingByValue())
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new))));
        Map<String, FlightGdansk> cheapestFlights = allFlightsSorted.entrySet().stream().map(entry -> Map.entry(entry.getKey(), entry.getValue().values().toArray()[0]))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> (FlightGdansk) entry.getValue()));
        return cheapestFlights.entrySet().stream()
                .sorted(comparingByValue()).collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    private Map<String, Map<String, FlightGdansk>> retrieveNames(FlightGdanskResponse response) {
        Map<String, Map<String, FlightGdansk>> names = new HashMap<>();
        response.getData().entrySet().forEach(entry -> {
            names.put(this.iataService.getCities().get(entry.getKey()),
                    entry.getValue().entrySet().stream()
                            .map(innerEntry -> Map.entry(innerEntry.getKey(), innerEntry.getValue().setAirline(this.iataService.getAirlines().get(innerEntry.getValue().getAirline()))))
                                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));
        });
        return names;
    }
}
