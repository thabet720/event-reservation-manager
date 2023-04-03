package com.attuned.events.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.attuned.events.dto.EventDTO;

@Service
public interface EventService {

	public List<EventDTO> getAllEvents(int pageNo, int pageSize);

}
