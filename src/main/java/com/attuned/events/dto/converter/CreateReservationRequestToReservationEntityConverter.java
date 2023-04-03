package com.attuned.events.dto.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.attuned.events.dto.request.CreateReservationRequest;
import com.attuned.events.entities.Event;
import com.attuned.events.entities.Reservation;
import com.attuned.events.exception.EventNotFoundException;
import com.attuned.events.repository.EventRepository;

@Component
public class CreateReservationRequestToReservationEntityConverter
		implements Converter<CreateReservationRequest, Reservation> {
	@Autowired
	private EventRepository eventRepository;

	@Override
	public Reservation convert(CreateReservationRequest source) {
		Reservation to = new Reservation();
		to.setClientName(source.getClientName());
		to.setReservedTickets(source.getReservedTickets());
		Optional<Event> optEvent = eventRepository.findById(source.getEventId());
		if (optEvent.isPresent()) {
			to.setEvent(optEvent.get());
		} else {
			throw new EventNotFoundException(source.getEventId());
		}
		return to;
	}

}
