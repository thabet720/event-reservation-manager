package com.attuned.events.service;

import org.springframework.stereotype.Service;

import com.attuned.events.dto.ReservationDTO;
import com.attuned.events.dto.request.CreateReservationRequest;
import com.attuned.events.dto.request.UpdateReservationRequest;

@Service
public interface ReservationService {
	public ReservationDTO createResevation(CreateReservationRequest request);

	public ReservationDTO updateReservation(UpdateReservationRequest request);

	public boolean cancelReservation(long reservationId);
}
