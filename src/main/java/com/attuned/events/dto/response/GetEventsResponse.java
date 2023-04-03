package com.attuned.events.dto.response;

import java.util.List;

import com.attuned.events.dto.EventDTO;

import lombok.Data;

@Data
public class GetEventsResponse extends BaseResponse {
	private List<EventDTO> events;
}
