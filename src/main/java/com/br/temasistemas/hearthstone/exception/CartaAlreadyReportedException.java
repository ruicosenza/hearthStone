package com.br.temasistemas.hearthstone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED)
public class CartaAlreadyReportedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3192691086804515010L;

	public CartaAlreadyReportedException( String exception) {
	    super(exception);
	}
}
