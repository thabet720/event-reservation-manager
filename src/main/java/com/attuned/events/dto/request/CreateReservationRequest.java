package com.attuned.events.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateReservationRequest {

	@JsonProperty(value = "client_name")
	@NotBlank(message = "is required")
	private String clientName;

	@JsonProperty(value = "reserved_tickets")
	@Positive(message = "There should be at least one ticket")
	private int reservedTickets;

	@JsonProperty(value = "event_id")
	@Positive(message = "is required")
	private long eventId;
}
