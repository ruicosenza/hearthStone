package com.br.temasistemas.hearthstone.enums;

public enum ETipoCarta {
	MAGIA("magia"),
	CRIATURA("criatura");
	
	private String tipo;
	
	ETipoCarta(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
}