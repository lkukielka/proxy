package com.flightsearch.proxy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightsearch.proxy.model.Airport;
import io.vavr.control.Try;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Service
public class AirportIata {
    public static Map<String, String> iatas;

    public AirportIata() {
        ObjectMapper mapper = new ObjectMapper();
        Airport[] airports = Try.of(() -> mapper.readValue(
                new File(getClass().getClassLoader().getResource("iata-codes.json").getFile()), Airport[].class
        )).get();
        this.iatas = asList(airports).stream().collect(
                Collectors.toMap(Airport::getCode, Airport::getName));
    }
}
