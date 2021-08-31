package com.popspot.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CurrencyNotSupportedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CurrencyNotSupportedException(String message) {
		super(message);
	}

	public CurrencyNotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}
}
