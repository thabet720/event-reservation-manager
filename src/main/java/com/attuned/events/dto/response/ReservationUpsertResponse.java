package com.attuned.events.dto.response;

import com.attuned.events.dto.ReservationDTO;

import lombok.Data;

@Data
public class ReservationUpsertResponse extends BaseResponse {
	public ReservationDTO reservation;
}
