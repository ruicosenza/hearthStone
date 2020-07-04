package com.br.temasistemas.hearthstone.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.br.temasistemas.hearthstone.dto.CartaDTO;

@Entity
public class Carta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5591158214141132646L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int id;
	@Column(nullable = false, unique = true)
	private String nome;
	@Column(nullable = false)
	private String descricao;
	@Column
	private int ataque;
	@Column
	private int defesa;
	@Column
	private String tipoCarta;
	
	public Carta() {
	}

	public Carta(CartaDTO cartaDTO) {
		this.id = cartaDTO.getId();
		this.nome = cartaDTO.getNome();
		this.descricao = cartaDTO.getDescricao();
		this.ataque = cartaDTO.getAtaque();
		this.defesa = cartaDTO.getDefesa();
		this.tipoCarta = cartaDTO.getTipo();
	}

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

	public String getTipoCarta() {
		return tipoCarta;
	}

	public void setTipoCarta(String tipoCarta) {
		this.tipoCarta = tipoCarta;
	}
}