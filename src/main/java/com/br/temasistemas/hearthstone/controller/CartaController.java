package com.br.temasistemas.hearthstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.temasistemas.hearthstone.model.Carta;
import com.br.temasistemas.hearthstone.repository.ICartasRepository;


@RestController("/carta")
public class CartaController {
	
	@Autowired
	private ICartasRepository repository;

	@GetMapping
	public ResponseEntity<Carta> cartas(Integer id) {
		//TODO
		return null;
//		PublicacaoApiDjeRJ publicacao = new PublicacaoApiDjeRJ();
//		try {
//			repository.findById(id);
//		} catch (Exception e) {
//			 
//			logger.error(e.toString());
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(publicacao);
//			
//		}
//
//		return ResponseEntity.status(HttpStatus.OK).body(publicacao);
	}
}