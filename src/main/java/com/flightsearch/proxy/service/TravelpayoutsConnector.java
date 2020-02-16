package com.flightsearch.proxy.service;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightsearch.proxy.model.Flight;
import com.flightsearch.proxy.model.TravelpayoutsResponse;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TravelpayoutsConnector {
    private final Properties properties;
    private final RestClient restClient;
    private final AirportIata airportIata;

    public TravelpayoutsConnector(AirportIata airportIata, RestClient restClient, Properties properties) {
        this.airportIata = airportIata;
        this.properties = properties;
        this.restClient = restClient;
    }

    public TravelpayoutsResponse getCheapestFlightsFromGdansk(String destination, String departDate, String returnDate) {
        return Try.of(() ->this.restClient.get(this.properties.getTravelpayoutsUrl()
                .replaceAll(":destination", destination)
                .replaceAll(":departDate", departDate)
                .replaceAll(":returnDate", returnDate),
                TravelpayoutsResponse.class
            )).get();
    }

    public  Map<String, Map<String, Flight>> getCheapestFlightFromGdanskWithAirports(String destination, String departDate, String returnDate) {
        return this.retrieveAirportNames(this.getCheapestFlightsFromGdansk(destination, departDate, returnDate));
    }

    private Map<String, Map<String, Flight>> retrieveAirportNames(TravelpayoutsResponse response) {
        Map<String, Map<String, Flight>> names = new HashMap<>();
        response.getData().entrySet().forEach(entry -> {
            names.put(this.airportIata.iatas.get(entry.getKey()), entry.getValue());
        });
        return names;
    }
}
