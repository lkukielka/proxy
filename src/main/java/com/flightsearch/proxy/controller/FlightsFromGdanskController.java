package com.flightsearch.proxy.controller;

import com.flightsearch.proxy.model.gdansk.FlightGdansk;
import com.flightsearch.proxy.model.gdansk.FlightGdanskResponse;
import com.flightsearch.proxy.service.gdansk.FlightsFromGdanskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/gdansk")
public class FlightsFromGdanskController {
    private final FlightsFromGdanskService flightsFromGdanskService;

    @Autowired
    public FlightsFromGdanskController(FlightsFromGdanskService travelpayoutsConnector) {
        this.flightsFromGdanskService = travelpayoutsConnector;
    }

    @GetMapping("")
    @ResponseBody
    public FlightGdanskResponse getFlights(@RequestParam(required = false, defaultValue = "-") String destination,
                                            @RequestParam String departureDate,
                                            @RequestParam String returnDate) {
        return this.flightsFromGdanskService.getFlightsFromGdansk(destination, departureDate, returnDate);
    }

    @GetMapping("/cheapest")
    @ResponseBody
    public Map<String, FlightGdansk>  getCheapestFlights(@RequestParam(required = false, defaultValue = "-") String destination,
                                            @RequestParam String departureDate,
                                            @RequestParam String returnDate) {
        return this.flightsFromGdanskService.getCheapestFlightsFromGdansk(destination, departureDate, returnDate);
    }
}
