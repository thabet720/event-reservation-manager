package com.attuned.events.exception;

public class ReservationNotFoundException extends RuntimeException {
	public ReservationNotFoundException() {
		super();
	}

	public ReservationNotFoundException(String message) {
		super(message);
	}

	public ReservationNotFoundException(long id) {
		super("reservation with id: " + id + " does not exist");
	}
}
