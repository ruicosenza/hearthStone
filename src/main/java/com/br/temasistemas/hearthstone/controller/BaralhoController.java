package com.br.temasistemas.hearthstone.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.temasistemas.hearthstone.dto.BaralhoDTO;
import com.br.temasistemas.hearthstone.dto.CartaDTO;
import com.br.temasistemas.hearthstone.enums.EClasseCarta;
import com.br.temasistemas.hearthstone.exception.BaralhoAlreadyReportedException;
import com.br.temasistemas.hearthstone.exception.BaralhoNotFoundException;
import com.br.temasistemas.hearthstone.model.Baralho;
import com.br.temasistemas.hearthstone.model.Carta;
import com.br.temasistemas.hearthstone.repository.IBaralhoRepository;

@RestController
@RequestMapping("/v1/baralho")
public class BaralhoController {

	Logger logger = LoggerFactory.getLogger(BaralhoController.class);

	@Autowired
	private IBaralhoRepository repository;

	@GetMapping
	public ResponseEntity<List<BaralhoDTO>> getAll() {
		List<BaralhoDTO> cartas = new BaralhoDTO().getListaBaralhoDTO(repository.findAll());
		return new ResponseEntity<List<BaralhoDTO>>(cartas, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<BaralhoDTO> getById(@RequestParam Integer id) {
		BaralhoDTO baralhoDTO = new BaralhoDTO();

		Optional<Baralho> baralho = repository.findById(id);
		if (!baralho.isPresent())
			throw new BaralhoNotFoundException("id-" + id);

		baralhoDTO = new BaralhoDTO(baralho.get());
		return ResponseEntity.status(HttpStatus.OK).body(baralhoDTO);
	}
	
	@GetMapping("/classe")
	public ResponseEntity<List<BaralhoDTO>> getByClasse(@RequestParam(value = "classe") String classe) {
		if( classe.equalsIgnoreCase("caçador") ) {
			classe = "cacador";
		}
		
		List<Baralho> baralho = repository.findByClasse(EClasseCarta.valueOf(classe.toUpperCase()));

		if (baralho.isEmpty())
			throw new BaralhoNotFoundException("classe-" + classe);

		List<BaralhoDTO> baralhoDTO = new ArrayList<>();
		baralho.stream().forEach(b -> baralhoDTO.add(new BaralhoDTO(b)));

		return ResponseEntity.status(HttpStatus.OK).body(baralhoDTO);
	}
	
	@GetMapping("/{id}/detalhar")
	public ResponseEntity<List<CartaDTO>> detalharCartas(@RequestParam Integer id) {
		Optional<Baralho> baralho = repository.findById(id);

		if (!baralho.isPresent())
			throw new BaralhoNotFoundException("id-" + id);

		List<Carta> cartas = baralho.get().getCartas();
		List<CartaDTO> cartasDTO = new ArrayList<>();
		cartas.stream().forEach(c -> cartasDTO.add(new CartaDTO(c)));
		
		return ResponseEntity.status(HttpStatus.OK).body(cartasDTO);
	}	
	
	@PostMapping
	public ResponseEntity<BaralhoDTO> save(@Valid @RequestBody BaralhoDTO baralhoDTO, UriComponentsBuilder uriBuilder)
			throws Exception {

		Baralho findByNome = repository.findByNome(baralhoDTO.getNome());

		if (findByNome != null) {
			throw new BaralhoAlreadyReportedException("O baralho já persistida: " + baralhoDTO.getNome());
		}

		baralhoDTO = new BaralhoDTO(repository.save(new Baralho(baralhoDTO)));
		
		URI uri = uriBuilder.path("/carta/{id}").buildAndExpand(baralhoDTO.getId()).toUri();
		
		return ResponseEntity.created(uri).body(baralhoDTO);
	}
}