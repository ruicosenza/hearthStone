package com.br.temasistemas.hearthstone.enums;

public enum ETipoCarta {
	MAGIA("magia");
	
	private String tipo;
	
	
	public enum CRIATURA {
		MAGO ("mago"),
		PALADINO("paladino"),
		CACADOR("caçador"),
		DRUIDA("druida"),
		QUALQUER("qualquer");
		
		
		private String tipo;

		CRIATURA(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return tipo;
		}
	};
	
	ETipoCarta(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
}