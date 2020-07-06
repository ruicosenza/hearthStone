package com.br.temasistemas.hearthstone.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.br.temasistemas.hearthstone.dto.BaralhoDTO;
import com.br.temasistemas.hearthstone.enums.EClasseCarta;

@Entity
public class Baralho implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3390509247862271284L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int id;
	@Column
	private String nome;
	@Column
	@Enumerated(EnumType.STRING)
	private EClasseCarta classe;
	@Column
	@OneToMany(fetch = FetchType.EAGER)
	private List<Carta> cartas;
	
	public Baralho() {
		// TODO Auto-generated constructor stub
	}
	
	public Baralho(BaralhoDTO baralhoDTO) {
		this.id = baralhoDTO.getId();
		this.nome = baralhoDTO.getNome();
		
		if( baralhoDTO.getClasse().equalsIgnoreCase("caçador") ) {
			this.classe = EClasseCarta.valueOf("CACADOR");
		} else {
			this.classe = EClasseCarta.valueOf(baralhoDTO.getClasse().toUpperCase());
		}
		
		this.cartas = baralhoDTO.cartasFromCartasDTO();
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
	public EClasseCarta getClasse() {
		return classe;
	}
	public void setClasse(EClasseCarta classe) {
		this.classe = classe;
	}
	public List<Carta> getCartas() {
		return cartas;
	}
	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}
}