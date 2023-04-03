package com.attuned.events.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.attuned.events.dto.EventDTO;
import com.attuned.events.entities.Event;

@Component
public class EventEntityToEventDTOConverter implements Converter<Event, EventDTO> {

	@Override
	public EventDTO convert(Event source) {
		EventDTO to = new EventDTO();
		to.setAvailability(source.getAvailability());
		to.setDate(source.getDate());
		to.setDescription(source.getDescription());
		to.setName(source.getName());
		to.setEventId(source.getEventId());
		return to;
	}

}
