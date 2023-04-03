package com.attuned.events.error;

import com.attuned.events.dto.response.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse extends BaseResponse {
	private String error;

	public ErrorResponse(String status, String error) {
		super(status);
		this.error = error;
	}
}
