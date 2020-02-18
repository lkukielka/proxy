package com.flightsearch.proxy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightsearch.proxy.model.Iata;
import io.vavr.control.Try;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

@Getter
@Service
public class IataService {
    private Map<String, String> cities;
    private Map<String, String> airlines;

    public IataService() {
        ObjectMapper mapper = new ObjectMapper();
        Iata[] cities = Try.of(() -> mapper.readValue(
                new File(getClass().getClassLoader().getResource("iata-cities.json").getFile()), Iata[].class)).get();
        this.cities = Arrays.stream(cities).collect(toMap(Iata::getCode, Iata::getName));

        Iata[] airlines = Try.of(() -> mapper.readValue(
                new File(getClass().getClassLoader().getResource("iata-airlines.json").getFile()), Iata[].class)).get();
        this.airlines = Arrays.stream(airlines).collect(toMap(Iata::getCode, Iata::getName));
    }
}
