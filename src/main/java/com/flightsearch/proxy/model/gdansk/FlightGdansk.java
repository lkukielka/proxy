package com.flightsearch.proxy.model.gdansk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FlightGdansk implements Comparable<FlightGdansk> {
    private BigDecimal price;
    private String airline;
    private String flightNumber;
    private LocalDateTime departureAt;
    private LocalDateTime returnAt;
    private LocalDateTime expiresAt;

    @Override
    public int compareTo(FlightGdansk flight) {
        return this.getPrice().compareTo(flight.getPrice());
    }
}
