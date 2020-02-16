package com.flightsearch.proxy.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class Properties {
    @Value("${travelpayouts.api.url}")
    private String travelpayoutsUrl;
    @Value("${version}")
    private String version;
}
