package com.br.temasistemas.hearthstone.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.temasistemas.hearthstone.dto.CartaDTO;
import com.br.temasistemas.hearthstone.model.Carta;
import com.br.temasistemas.hearthstone.repository.ICartasRepository;

@RestController
@RequestMapping("/carta/v1")
public class CartaController {
	Logger logger = LoggerFactory.getLogger(CartaController.class);

	@Autowired
	private ICartasRepository repository;
	
	@GetMapping
	public ResponseEntity<List<CartaDTO>> getAll() {
		List<CartaDTO> cartas = new CartaDTO().getListaCartas(repository.findAll());
		return new ResponseEntity<List<CartaDTO>>(cartas, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<CartaDTO> getById(@RequestParam Integer id) {
		CartaDTO cartaDTO = new CartaDTO();

		try {
			cartaDTO = new CartaDTO(repository.findById(id).get());
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<CartaDTO>(HttpStatus.NOT_FOUND);

		}

		return ResponseEntity.status(HttpStatus.OK).body(cartaDTO);
	}

	@PostMapping
	public ResponseEntity<CartaDTO> save(@RequestBody CartaDTO cartaDTO, UriComponentsBuilder uriBuilder) {
		cartaDTO = new CartaDTO(repository.save(new Carta(cartaDTO)));

		URI uri = uriBuilder.path("/carta/{id}").buildAndExpand(cartaDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(cartaDTO);
	}
	
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<CartaDTO> deleteById(@PathVariable Integer id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<CartaDTO>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<CartaDTO>(HttpStatus.NOT_FOUND);
		}
	}
}