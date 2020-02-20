package com.flightsearch.proxy.model.flights;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class FlightsSearchCriteria {
    String origin;
    String destination;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    Date startDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    Date returnDate;
    long minDuration;
    long maxDuration;
}
