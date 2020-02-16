package com.flightsearch.proxy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TravelpayoutsResponse {
    private boolean success;
    private Map<String, Map<String, Flight>> data;
    private String error;
    private String currency;

}