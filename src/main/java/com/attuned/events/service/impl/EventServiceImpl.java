package com.attuned.events.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.attuned.events.dto.EventDTO;
import com.attuned.events.entities.Event;
import com.attuned.events.repository.EventRepository;
import com.attuned.events.service.EventService;

@Service
public class EventServiceImpl implements EventService {
	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ConversionService conversionService;

	@Override
	public List<EventDTO> getAllEvents(int pageNo, int pageSize) {
		Pageable page = PageRequest.of(pageNo, pageSize);
		TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Event.class));
		TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(EventDTO.class));
		return (List<EventDTO>) conversionService.convert(eventRepository.findAll(page).getContent(), sourceType,
				targetType);
	}

}
