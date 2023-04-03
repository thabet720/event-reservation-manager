package com.attuned.events.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EventDTO {

	private Long eventId;

	private String name;

	private String description;

	private int availability;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern  = "yyyy-MM-dd'T'hh:mm:ssZ")
	private ZonedDateTime date;

}
