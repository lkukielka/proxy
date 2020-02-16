package com.flightsearch.proxy.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Flight {
    private BigDecimal price;
    private String airline;
    private String flightNumber;
    private LocalDateTime departureAt;
    private LocalDateTime returnAt;
    private LocalDateTime expiresAt;
}