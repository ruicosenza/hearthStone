package com.br.temasistemas.hearthstone.dto;

import com.br.temasistemas.hearthstone.model.Carta;

public class CartaDTO {

	private String nome;
	private String descricao;
	private int ataque;
	private int defesa;
	private String tipo;
	private String classe;
	
	public CartaDTO(Carta carta) {
		this.nome = carta.getNome();
		this.descricao = carta.getDescricao();
		this.ataque = carta.getAtaque();
		this.defesa = carta.getDefesa();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getAtaque() {
		return ataque;
	}
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
	public int getDefesa() {
		return defesa;
	}
	public void setDefesa(int defesa) {
		this.defesa = defesa;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
}