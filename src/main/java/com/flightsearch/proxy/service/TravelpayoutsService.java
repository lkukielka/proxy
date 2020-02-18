package com.flightsearch.proxy.service;

import com.flightsearch.proxy.model.Flight;
import com.flightsearch.proxy.model.TravelpayoutsResponse;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

@Service
public class TravelpayoutsService {
    private final PropertiesService properties;
    private final RestClientService restClient;
    private final IataService iataService;

    @Autowired
    public TravelpayoutsService(IataService airportIata, RestClientService restClient, PropertiesService properties) {
        this.iataService = airportIata;
        this.properties = properties;
        this.restClient = restClient;
    }

    public TravelpayoutsResponse getFlightsFromGdansk(String destination, String departDate, String returnDate) {
        return Try.of(() ->this.restClient.get(this.properties.getTravelpayoutsUrl()
                .replaceAll(":destination", destination)
                .replaceAll(":departDate", departDate)
                .replaceAll(":returnDate", returnDate),
                TravelpayoutsResponse.class
            )).get();
    }

    public Map<String, Flight> getCheapestFlightsFromGdansk(String destination, String departDate, String returnDate) {
        Map<String, Map<String, Flight>> allFlightsWithNames =
                this.retrieveNames(this.getFlightsFromGdansk(destination, departDate, returnDate));
        Map<String, Map<String, Flight>> allFlightsSorted = allFlightsWithNames.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().entrySet().stream()
                            .sorted(comparingByValue())
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new))));
        Map<String, Flight> cheapestFlights = allFlightsSorted.entrySet().stream().map(entry -> Map.entry(entry.getKey(), entry.getValue().values().toArray()[0]))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> (Flight) entry.getValue()));
        return cheapestFlights.entrySet().stream()
                .sorted(comparingByValue()).collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    public  Map<String, Map<String, Flight>> getCheapestFlightFromGdanskWithAirports(String destination, String departDate, String returnDate) {
        return this.retrieveNames(this.getFlightsFromGdansk(destination, departDate, returnDate));
    }

    private Map<String, Map<String, Flight>> retrieveNames(TravelpayoutsResponse response) {
        Map<String, Map<String, Flight>> names = new HashMap<>();
        response.getData().entrySet().forEach(entry -> {
            names.put(this.iataService.getCities().get(entry.getKey()),
                    entry.getValue().entrySet().stream()
                            .map(innerEntry -> Map.entry(innerEntry.getKey(), innerEntry.getValue().setAirline(this.iataService.getAirlines().get(innerEntry.getValue().getAirline()))))
                                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));
        });
        return names;
    }
}
