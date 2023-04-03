package com.attuned.events.dto.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.attuned.events.dto.request.UpdateReservationRequest;
import com.attuned.events.entities.Reservation;
import com.attuned.events.exception.ReservationNotFoundException;
import com.attuned.events.repository.ReservationRepository;

@Component
public class UpdateReservationRequestToReservationEntityConverter
		implements Converter<UpdateReservationRequest, Reservation> {
	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public Reservation convert(UpdateReservationRequest source) {
		Optional<Reservation> optReservation = reservationRepository.findById(source.getReservationId());
		if (!optReservation.isPresent()) {
			throw new ReservationNotFoundException(source.getReservationId());
		}
		Reservation to = new Reservation();
		to.setClientName(optReservation.get().getClientName());
		to.setEvent(optReservation.get().getEvent());
		to.setReservationId(source.getReservationId());
		to.setReservedTickets(source.getReservedTickets());
		return to;
	}

}
