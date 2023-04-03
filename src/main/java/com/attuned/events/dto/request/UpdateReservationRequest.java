package com.attuned.events.dto.request;

import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateReservationRequest {

	@Positive(message = "must be positive")
	@JsonIgnore
	private long reservationId;

	@JsonProperty(value = "reserved_tickets")
	@Positive(message = "There should be at least one ticket")
	private int reservedTickets;

}
