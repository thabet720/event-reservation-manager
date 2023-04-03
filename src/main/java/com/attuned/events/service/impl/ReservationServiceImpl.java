package com.attuned.events.service.impl;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.attuned.events.constants.ActionConstants;
import com.attuned.events.dto.ReservationDTO;
import com.attuned.events.dto.request.CreateReservationRequest;
import com.attuned.events.dto.request.UpdateReservationRequest;
import com.attuned.events.entities.Event;
import com.attuned.events.entities.Reservation;
import com.attuned.events.exception.RequestValidationException;
import com.attuned.events.exception.ReservationNotFoundException;
import com.attuned.events.repository.EventRepository;
import com.attuned.events.repository.ReservationRepository;
import com.attuned.events.service.ReservationService;
import com.attuned.events.validation.util.BeanValidatorUtil;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private ConversionService conversionService;
	@Autowired
	private EventRepository eventRepository;

	@Override
	public ReservationDTO createResevation(CreateReservationRequest request) {
		BeanValidatorUtil.validate(request);
		Reservation reservation = conversionService.convert(request, Reservation.class);
		if (!areTicketsAvailable(reservation.getEvent(),reservation.getReservedTickets())) {
			throw new RequestValidationException("The Amount of requested tickets exceeds the available amount");
		}
		if (reservation.getEvent().getDate().isBefore(ZonedDateTime.now())) {
			throw new RequestValidationException("Requested Event has ended");
		}
		reservation = reservationRepository.save(reservation);
		updateEventTicketStock(reservation, null, ActionConstants.CREATE_RESERVATION);
		return conversionService.convert(reservation, ReservationDTO.class);

	}

	@Override
	public ReservationDTO updateReservation(UpdateReservationRequest request) {
		BeanValidatorUtil.validate(request);
		Reservation reservation = conversionService.convert(request, Reservation.class);
		Reservation oldReservation = new Reservation();
		oldReservation
				.setReservedTickets(reservationRepository.getById(request.getReservationId()).getReservedTickets());
		if (reservation.getEvent().getDate().isBefore(ZonedDateTime.now())) {
			throw new RequestValidationException("Requested Event has ended");
		}
		if (!areTicketsAvailable(reservation.getEvent(),reservation.getReservedTickets()-oldReservation.getReservedTickets())) {
			throw new RequestValidationException("The Amount of requested tickets exceeds the available amount");
		}

		Reservation updatedReservation = reservationRepository.save(reservation);
		updateEventTicketStock(reservation, oldReservation, ActionConstants.UPDATE_RESERVEATION);
		return conversionService.convert(updatedReservation, ReservationDTO.class);
	}

	@Override
	public boolean cancelReservation(long reservationId) {
		Optional<Reservation> reservation = reservationRepository.findById(reservationId);
		if (reservation.isPresent()) {
			reservationRepository.deleteById(reservationId);
			updateEventTicketStock(reservation.get(), null, ActionConstants.CANCEL_RESERVEATION);
		} else {
			throw new ReservationNotFoundException(reservationId);
		}
		return true;
	}

	private void updateEventTicketStock(Reservation reservation, Reservation oldReservation, String action) {
		Event event = reservation.getEvent();
		switch (action) {
		case ActionConstants.CREATE_RESERVATION:
			event.setAvailability(event.getAvailability() - reservation.getReservedTickets());
			break;
		case ActionConstants.UPDATE_RESERVEATION:
			int diff = oldReservation.getReservedTickets() - reservation.getReservedTickets();
			event.setAvailability(event.getAvailability() + diff);
			break;
		case ActionConstants.CANCEL_RESERVEATION:
			event.setAvailability(event.getAvailability() + reservation.getReservedTickets());
			break;
		default:
			break;
		}
		eventRepository.save(event);
	}

	private boolean areTicketsAvailable(Event event, int diff) {
		if (event.getAvailability() < diff) {
			return false;
		}
		return true;
	}

}
