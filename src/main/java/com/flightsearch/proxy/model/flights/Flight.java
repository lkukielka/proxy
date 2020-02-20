package com.flightsearch.proxy.model.flights;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Flight {
	private String value;
	private String origin;
	private String destination;
	private String gate;
	private String duration;
	private long numberOfChanges;
	private Date departDate;
}
