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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.temasistemas.hearthstone.dto.BaralhoDTO;
import com.br.temasistemas.hearthstone.dto.CartaDTO;
import com.br.temasistemas.hearthstone.enums.EClasseCarta;
import com.br.temasistemas.hearthstone.exception.BaralhoLimiteCartasException;
import com.br.temasistemas.hearthstone.exception.BaralhoNotFoundException;
import com.br.temasistemas.hearthstone.model.Baralho;
import com.br.temasistemas.hearthstone.model.Carta;
import com.br.temasistemas.hearthstone.repository.IBaralhoRepository;
import com.br.temasistemas.hearthstone.repository.ICartasRepository;

@RestController
@RequestMapping("/v1/baralho")
public class BaralhoController {

	Logger logger = LoggerFactory.getLogger(BaralhoController.class);

	@Autowired
	private IBaralhoRepository repository;
	@Autowired
	private ICartasRepository cartaRepository;

	@GetMapping
	public ResponseEntity<List<BaralhoDTO>> getAll() {
		List<BaralhoDTO> cartas = new BaralhoDTO().listaBaralhoDTO(repository.findAll());
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
	
	@GetMapping("/nome")
	public ResponseEntity<BaralhoDTO> getByNome(@RequestParam(value = "nome") String nome) {
		Optional<Baralho> baralho = repository.findByNome(nome);

		if (!baralho.isPresent())
			throw new BaralhoNotFoundException("classe-" + nome);

		BaralhoDTO baralhoDTO = new BaralhoDTO(baralho.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(baralhoDTO);
	}

	@GetMapping("/classe")
	public ResponseEntity<List<BaralhoDTO>> getByClasse(@RequestParam(value = "classe") String classe) {
		if (classe.equalsIgnoreCase("caçador")) {
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
	public ResponseEntity<List<CartaDTO>> detalharCartas(@RequestParam String id) {
		Optional<Baralho> baralho = repository.findById(Integer.valueOf(id));

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
		Baralho baralho;
		Optional<Baralho> findByNome = repository.findByNome(baralhoDTO.getNome());

		List<Carta> cartas = new ArrayList<>();
		
		for (CartaDTO carta : baralhoDTO.getCartas()) {
			Optional<Carta> findById = cartaRepository.findByNome(carta.getNome());
			
			if (findById.isPresent()) {
				cartas.add(findById.get());
			}
		}
		
		if( cartas.size() > 30 ) {
			throw new BaralhoLimiteCartasException("Limite de cartas em um baralho é de 30 cartas");
		}

		if (findByNome.isPresent()) {
			baralho =  findByNome.get();
			baralho.setCartas(cartas);
			baralhoDTO = new BaralhoDTO(repository.save(baralho));
			
			URI uri = uriBuilder.path("/carta/{id}").buildAndExpand(baralhoDTO.getId()).toUri();

			return ResponseEntity.created(uri).body(baralhoDTO);
		}


		baralho = new Baralho(baralhoDTO);
		baralho.setCartas(cartas);

		baralhoDTO = new BaralhoDTO(repository.save(baralho));

		URI uri = uriBuilder.path("/carta/{id}").buildAndExpand(baralhoDTO.getId()).toUri();

		return ResponseEntity.created(uri).body(baralhoDTO);
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<BaralhoDTO> deleteById(@PathVariable Integer id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<BaralhoDTO>(HttpStatus.OK);
		} catch (Exception e) {
			throw new BaralhoNotFoundException("id-" + id);
		}
	}
}