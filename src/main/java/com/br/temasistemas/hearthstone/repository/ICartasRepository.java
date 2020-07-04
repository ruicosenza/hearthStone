package com.br.temasistemas.hearthstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.temasistemas.hearthstone.model.Carta;

@Repository
public interface ICartasRepository extends JpaRepository<Carta, Integer> {

}