package com.br.temasistemas.hearthstone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartaNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 983243831565824044L;

	public CartaNotFoundException(String exception) {
	    super(exception);
	}
}