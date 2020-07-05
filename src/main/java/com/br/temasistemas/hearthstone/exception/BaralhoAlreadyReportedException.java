package com.br.temasistemas.hearthstone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED)
public class BaralhoAlreadyReportedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3192691086804515010L;

	public BaralhoAlreadyReportedException( String exception) {
	    super(exception);
	}
}
