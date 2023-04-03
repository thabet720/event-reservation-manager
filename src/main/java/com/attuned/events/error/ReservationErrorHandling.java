package com.attuned.events.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.attuned.events.constants.ResponseConstants;
import com.attuned.events.exception.EventNotFoundException;
import com.attuned.events.exception.RequestValidationException;
import com.attuned.events.exception.ReservationNotFoundException;

@ControllerAdvice
public class ReservationErrorHandling {
	@ExceptionHandler(value = ReservationNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleReservationNotFoundException(ReservationNotFoundException ex) {

		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ResponseConstants.FAILURE, ex.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = EventNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEventNotFoundException(EventNotFoundException ex) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ResponseConstants.FAILURE, ex.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = RequestValidationException.class)
	public ResponseEntity<ErrorResponse> handleRequestValidationException(RequestValidationException ex) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ResponseConstants.FAILURE, ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

}
