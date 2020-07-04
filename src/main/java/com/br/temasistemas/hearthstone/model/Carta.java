package com.br.temasistemas.hearthstone.model;

import java.io.Serializable;

import com.br.temasistemas.hearthstone.enums.ETipoCarta;

public class Carta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5591158214141132646L;

	private int id;
	private String nome;
	private String descricao;
	private int ataque;
	private int defesa;
	
	private ETipoCarta tipoCarta;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public ETipoCarta getTipoCarta() {
		return tipoCarta;
	}
	public void setTipoCarta(ETipoCarta tipoCarta) {
		this.tipoCarta = tipoCarta;
	}
}