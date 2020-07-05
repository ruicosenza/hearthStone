package com.br.temasistemas.hearthstone.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.br.temasistemas.hearthstone.model.Baralho;
import com.br.temasistemas.hearthstone.model.Carta;

public class BaralhoDTO {

	private int id;
	private String nome;
	@Enumerated(EnumType.STRING)
	private String classe;
	private List<CartaDTO> cartas;
	
	public BaralhoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public BaralhoDTO(Baralho baralho) {
		this.id = baralho.getId();
		this.nome = baralho.getNome();
		this.classe = baralho.getClasse().getClasse();
		this.cartas = getListaCartasDTOBaralhoDTO(baralho.getCartas());
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
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public List<CartaDTO> getCartas() {
		return cartas;
	}
	public void setCartas(List<CartaDTO> cartas) {
		this.cartas = cartas;
	}
	
	public List<CartaDTO> getListaCartasDTOBaralhoDTO(List<Carta> cartas) {
		List<CartaDTO> cartasDTO = new ArrayList<CartaDTO>();

		cartas.stream().forEachOrdered(b -> cartasDTO.add(new CartaDTO(b)));
		return cartasDTO;
	}

	public List<BaralhoDTO> getListaBaralhoDTO(List<Baralho> baralho) {
		List<BaralhoDTO> baralhoDTO = new ArrayList<>();
		
		baralho.stream().forEachOrdered(b -> baralhoDTO.add(new BaralhoDTO(b)));
		return baralhoDTO;
	}

	public List<Carta> getCartasFromCartasDTO() {
		List<Carta> cartas = new ArrayList<Carta>();
		
		this.getCartas().stream().forEach(c -> cartas.add(new Carta(c)));
		return cartas;
	}
}