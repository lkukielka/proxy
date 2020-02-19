package com.flightsearch.proxy.model.gdansk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flightsearch.proxy.model.gdansk.FlightGdansk;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightGdanskResponse {
    private boolean success;
    private Map<String, Map<String, FlightGdansk>> data;
    private String error;
    private String currency;

}