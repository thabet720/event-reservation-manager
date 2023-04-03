package com.attuned.events.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.attuned.events.dto.ReservationDTO;
import com.attuned.events.entities.Reservation;

@Component
public class ReservationEntityToReservationDTOConverter implements Converter<Reservation, ReservationDTO> {

	@Override
	public ReservationDTO convert(Reservation source) {
		ReservationDTO to = new ReservationDTO();
		to.setId(source.getReservationId());
		to.setClientName(source.getClientName());
		to.setReservedTickets(source.getReservedTickets());
		to.setEventId(source.getEvent().getEventId());
		return to;
	}

}
