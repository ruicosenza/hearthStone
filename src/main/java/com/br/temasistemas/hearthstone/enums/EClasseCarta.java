package com.br.temasistemas.hearthstone.enums;

public enum EClasseCarta {
	MAGO ("mago"),
	PALADINO("paladino"),
	CACADOR("caçador"),
	DRUIDA("druida"),
	QUALQUER("qualquer");
	
	
	private String classe;

	EClasseCarta(String classe) {
		this.classe = classe;
	}

	public String getClasse() {
		return classe;
	}
}
