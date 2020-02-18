package com.flightsearch.proxy.controller;

import com.flightsearch.proxy.model.Flight;
import com.flightsearch.proxy.model.TravelpayoutsResponse;
import com.flightsearch.proxy.service.TravelpayoutsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/flights")
public class TravelpayoutsController {
    private final TravelpayoutsService travelpayoutsConnector;

    @Autowired
    public TravelpayoutsController(TravelpayoutsService travelpayoutsConnector) {
        this.travelpayoutsConnector = travelpayoutsConnector;
    }

    @GetMapping("")
    @ResponseBody
    public TravelpayoutsResponse getFlights(@RequestParam(required = false, defaultValue = "-") String destination,
                                            @RequestParam String departureDate,
                                            @RequestParam String returnDate) {
        return this.travelpayoutsConnector.getFlightsFromGdansk(destination, departureDate, returnDate);
    }

    @GetMapping("/cheapest")
    @ResponseBody
    public Map<String, Flight>  getCheapestFlights(@RequestParam(required = false, defaultValue = "-") String destination,
                                            @RequestParam String departureDate,
                                            @RequestParam String returnDate) {
        return this.travelpayoutsConnector.getCheapestFlightsFromGdansk(destination, departureDate, returnDate);
    }

    @GetMapping("/names")
    @ResponseBody
    public Map<String, Map<String, Flight>> getFlightsWithAirports(@RequestParam(required = false, defaultValue = "-") String destination,
                                                       @RequestParam String departureDate,
                                                       @RequestParam String returnDate) {
        return this.travelpayoutsConnector.getCheapestFlightFromGdanskWithAirports(destination, departureDate, returnDate);
    }
}
