package com.br.temasistemas.hearthstone.repository;

import org.springframework.data.repository.CrudRepository;

import com.br.temasistemas.hearthstone.model.Carta;

public interface ICartasRepository extends CrudRepository<Carta, Integer> {

}