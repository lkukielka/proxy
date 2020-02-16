package com.flightsearch.proxy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightsearch.proxy.model.Airport;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Service
public class AirportIataService {
    public static Map<String, String> iatas;

    public AirportIataService() {
        ObjectMapper mapper = new ObjectMapper();
        Airport[] airports = Try.of(() -> mapper.readValue(
                new File(getClass().getClassLoader().getResource("iata-codes.json").getFile()), Airport[].class
        )).get();
        this.iatas = asList(airports).stream().collect(
                Collectors.toMap(Airport::getCode, Airport::getName));
    }
}
