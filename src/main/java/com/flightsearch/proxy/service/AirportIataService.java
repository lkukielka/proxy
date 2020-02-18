package com.flightsearch.proxy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightsearch.proxy.model.Airport;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

@Service
public class AirportIataService {
    public Map<String, String> iatas;

    public AirportIataService() {
        ObjectMapper mapper = new ObjectMapper();
        Airport[] airports = Try.of(() -> mapper.readValue(
                new File(getClass().getClassLoader().getResource("iata-codes.json").getFile()), Airport[].class
        )).get();
        this.iatas = Arrays.stream(airports).collect(toMap(Airport::getCode, Airport::getName));
    }
}
