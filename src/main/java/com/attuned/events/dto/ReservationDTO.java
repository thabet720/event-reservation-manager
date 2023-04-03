package com.attuned.events.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReservationDTO {
	private long id;
	@JsonProperty(value = "client_name")
	private String clientName;
	@JsonProperty(value = "reserved_tickets")
	private int reservedTickets;
	@JsonProperty(value = "event_id")
	private long eventId;
}
