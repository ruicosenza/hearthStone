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

import com.br.temasistemas.hearthstone.dto.CartaDTO;
import com.br.temasistemas.hearthstone.enums.EClasseCarta;
import com.br.temasistemas.hearthstone.enums.ETipoCarta;
import com.br.temasistemas.hearthstone.exception.CartaAlreadyReportedException;
import com.br.temasistemas.hearthstone.exception.CartaNotFoundException;
import com.br.temasistemas.hearthstone.model.Carta;
import com.br.temasistemas.hearthstone.repository.ICartasRepository;

@RestController
@RequestMapping("/v1/carta")
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

		Optional<Carta> carta = repository.findById(id);
		if (!carta.isPresent())
			throw new CartaNotFoundException("id-" + id);

		cartaDTO = new CartaDTO(carta.get());
		return ResponseEntity.status(HttpStatus.OK).body(cartaDTO);

	}

	@GetMapping("/nome")
	public ResponseEntity<CartaDTO> getByNome(@RequestParam(value = "nome") String nome) {
		CartaDTO cartaDTO = new CartaDTO();

		Optional<Carta> carta = repository.findByNome(nome);

		if (!carta.isPresent())
			throw new CartaNotFoundException("nome-" + nome);

		cartaDTO = new CartaDTO(carta.get());

		return ResponseEntity.status(HttpStatus.OK).body(cartaDTO);
	}

	@GetMapping("/classe")
	public ResponseEntity<List<CartaDTO>> getByClasse(@RequestParam(value = "classe") String classe) {
		if (classe.equalsIgnoreCase("caçador")) {
			classe = "cacador";
		}

		List<Carta> cartas = repository.findByClasse(EClasseCarta.valueOf(classe.toUpperCase()));

		if (cartas.isEmpty())
			throw new CartaNotFoundException("classe-" + classe);

		List<CartaDTO> cartasDTO = new ArrayList<>();
		cartas.stream().forEach(c -> cartasDTO.add(new CartaDTO(c)));

		return ResponseEntity.status(HttpStatus.OK).body(cartasDTO);
	}

	@GetMapping("/tipo")
	public ResponseEntity<List<CartaDTO>> getByTipo(@RequestParam(value = "tipo") String tipo) {
		List<Carta> cartas = repository.findByTipo(ETipoCarta.valueOf(tipo.toUpperCase()));

		if (cartas.isEmpty())
			throw new CartaNotFoundException("tipo-" + tipo);

		List<CartaDTO> cartasDTO = new ArrayList<>();
		cartas.stream().forEach(c -> cartasDTO.add(new CartaDTO(c)));

		return ResponseEntity.status(HttpStatus.OK).body(cartasDTO);
	}

	@PostMapping
	public ResponseEntity<CartaDTO> save(@Valid @RequestBody CartaDTO cartaDTO, UriComponentsBuilder uriBuilder)
			throws Exception {

		Optional<Carta> findByNome = repository.findByNome(cartaDTO.getNome());

		if (findByNome.isPresent()) {
			throw new CartaAlreadyReportedException("A carta já persistida: " + cartaDTO.getNome());
		}

		Carta save = repository.save(new Carta(cartaDTO));
		cartaDTO = new CartaDTO(save);

		URI uri = uriBuilder.path("/carta/{id}").buildAndExpand(cartaDTO.getId()).toUri();

		return ResponseEntity.created(uri).body(cartaDTO);
	}

	@PostMapping("/addCartas")
	public ResponseEntity<List<CartaDTO>> save(@Valid @RequestBody ArrayList<CartaDTO> cartasDTO,
			UriComponentsBuilder uriBuilder) throws Exception {
		List<CartaDTO> cartasDTORetorno = new ArrayList<CartaDTO>();
		Optional<Carta> findByNome;
		Carta save;

		for (CartaDTO cartaDTO : cartasDTO) {

			findByNome = repository.findByNome(cartaDTO.getNome());

			if (!findByNome.isPresent()) {
				save = repository.save(new Carta(cartaDTO));
				cartaDTO = new CartaDTO(save);

				cartasDTORetorno.add(cartaDTO);
			}
		}

		return ResponseEntity.ok(cartasDTORetorno);
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<CartaDTO> deleteById(@PathVariable Integer id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<CartaDTO>(HttpStatus.OK);
		} catch (Exception e) {
			throw new CartaNotFoundException("id-" + id);
		}
	}
}