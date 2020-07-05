package com.br.temasistemas.hearthstone.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.br.temasistemas.hearthstone.dto.CartaDTO;
import com.br.temasistemas.hearthstone.enums.EClasseCarta;
import com.br.temasistemas.hearthstone.enums.ETipoCarta;

@Entity
public class Carta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5591158214141132646L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int id;
	@NotBlank(message = "Nome é obrigatório")
	@Column(nullable = false, unique = true)
	private String nome;
	@NotBlank(message = "Descrição é obrigatório")
	@Column(nullable = false)
	private String descricao;
	@Column
	@Min(0)
	@Max(10)
	private int ataque;
	@Column
	@Min(0)
	@Max(10)
	private int defesa;
	@Column
	@Enumerated(EnumType.STRING)
	private ETipoCarta tipo;
	@Column
	@Enumerated(EnumType.STRING)
	private EClasseCarta classe;
	
	public Carta() {
	}

	public Carta(CartaDTO cartaDTO) {
		this.id = cartaDTO.getId();
		this.nome = cartaDTO.getNome();
		this.descricao = cartaDTO.getDescricao();
		this.ataque = cartaDTO.getAtaque();
		this.defesa = cartaDTO.getDefesa();
		this.tipo = ETipoCarta.valueOf(cartaDTO.getTipo().toUpperCase());
		
		if( cartaDTO.getClasse().equalsIgnoreCase("caçador") ) {
			this.classe = EClasseCarta.valueOf("CACADOR");
		} else {
			this.classe = EClasseCarta.valueOf(cartaDTO.getClasse().toUpperCase());
		}
		
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

	public ETipoCarta getTipo() {
		return tipo;
	}

	public void setTipo(ETipoCarta tipo) {
		this.tipo = tipo;
	}

	public EClasseCarta getClasse() {
		return classe;
	}

	public void setClasse(EClasseCarta classe) {
		this.classe = classe;
	}
}