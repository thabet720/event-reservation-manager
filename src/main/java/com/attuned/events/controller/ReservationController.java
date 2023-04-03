package com.attuned.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.attuned.events.constants.ResponseConstants;
import com.attuned.events.dto.request.CreateReservationRequest;
import com.attuned.events.dto.request.UpdateReservationRequest;
import com.attuned.events.dto.response.ReservationUpsertResponse;
import com.attuned.events.service.ReservationService;

@RestController
public class ReservationController {
	@Autowired
	private ReservationService reservationService;

	@PostMapping(value = "/reservation",produces = "application/json",consumes = "application/json")
	public ResponseEntity<ReservationUpsertResponse> createReservation(@RequestBody CreateReservationRequest request) {
		ReservationUpsertResponse response = new ReservationUpsertResponse();
		response.setReservation(reservationService.createResevation(request));
		response.setStatus(ResponseConstants.SUCCESS);
		return new ResponseEntity<ReservationUpsertResponse>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/reservation/{id}",produces = "application/json", consumes = "application/json")
	public ResponseEntity<ReservationUpsertResponse> updateReservation(@RequestBody UpdateReservationRequest request,
			@PathVariable(name = "id") long reservationId) {
		ReservationUpsertResponse response = new ReservationUpsertResponse();
		request.setReservationId(reservationId);
		response.setReservation(reservationService.updateReservation(request));
		response.setStatus(ResponseConstants.SUCCESS);
		return new ResponseEntity<ReservationUpsertResponse>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/reservation/{id}",produces = "application/json")
	public ResponseEntity<ReservationUpsertResponse> cancelReservation(@PathVariable(name = "id") long reservationId) {
		reservationService.cancelReservation(reservationId);
		ReservationUpsertResponse response = new ReservationUpsertResponse();
		response.setReservation(null);
		response.setStatus(ResponseConstants.SUCCESS);
		return new ResponseEntity<ReservationUpsertResponse>(response, HttpStatus.OK);

	}
}
