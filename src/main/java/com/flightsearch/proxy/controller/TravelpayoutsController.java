package com.flightsearch.proxy.controller;

import com.flightsearch.proxy.model.Flight;
import com.flightsearch.proxy.model.TravelpayoutsResponse;
import com.flightsearch.proxy.service.TravelpayoutsConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TravelpayoutsController {
    private final TravelpayoutsConnector travelpayoutsConnector;

    public TravelpayoutsController(TravelpayoutsConnector travelpayoutsConnector) {
        this.travelpayoutsConnector = travelpayoutsConnector;
    }

    @GetMapping("/flights")
    @ResponseBody
    public TravelpayoutsResponse getFlights(@RequestParam(required = false, defaultValue = "-") String destination,
                                            @RequestParam String departureDate,
                                            @RequestParam String returnDate) {
        return this.travelpayoutsConnector.getCheapestFlightsFromGdansk(destination, departureDate, returnDate);
    }

    @GetMapping("/flightsWithAirports")
    @ResponseBody
    public Map<String, Map<String, Flight>> getFlightsWithAirports(@RequestParam(required = false, defaultValue = "-") String destination,
                                                       @RequestParam String departureDate,
                                                       @RequestParam String returnDate) {
        return this.travelpayoutsConnector.getCheapestFlightFromGdanskWithAirports(destination, departureDate, returnDate);
    }
}
