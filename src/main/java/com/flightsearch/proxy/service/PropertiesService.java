package com.flightsearch.proxy.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class PropertiesService {
    @Value("${travelpayouts.api.url.gdansk}")
    private String travelpayoutsUrlGdansk;
    @Value(("${travelpayouts.api.url.flights}"))
    private String travelpayoutsFlightsUrl;
    @Value("${version}")
    private String version;
}
