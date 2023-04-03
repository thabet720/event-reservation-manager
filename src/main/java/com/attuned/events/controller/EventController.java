package com.attuned.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.attuned.events.constants.ResponseConstants;
import com.attuned.events.dto.response.GetEventsResponse;
import com.attuned.events.service.EventService;

@RestController
public class EventController {
	@Autowired
	private EventService eventService;

	@GetMapping(value = "/event",produces = "application/json")
	public ResponseEntity<GetEventsResponse> getAllEvents(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize) {
		GetEventsResponse response = new GetEventsResponse();
		response.setEvents(eventService.getAllEvents(pageNo, pageSize));
		response.setStatus(ResponseConstants.SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
