package com.br.temasistemas.hearthstone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BaralhoLimiteCartasException extends RuntimeException {

	private static final long serialVersionUID = 4468113404020685866L;

	public BaralhoLimiteCartasException( String exception) {
	    super(exception);
	}
}
