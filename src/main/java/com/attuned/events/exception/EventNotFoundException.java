package com.attuned.events.exception;

public class EventNotFoundException extends RuntimeException {
	public EventNotFoundException() {
		super();
	}

	public EventNotFoundException(String message) {
		super(message);
	}

	public EventNotFoundException(long id) {
		super("event with id: " + id + " does not exist");
	}
}
