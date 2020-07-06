package com.br.temasistemas.hearthstone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.temasistemas.hearthstone.enums.EClasseCarta;
import com.br.temasistemas.hearthstone.enums.ETipoCarta;
import com.br.temasistemas.hearthstone.model.Carta;

@Repository
public interface ICartasRepository extends JpaRepository<Carta, Integer> {

	public Optional<Carta> findByNome( String nome );
	public List<Carta> findByClasse( EClasseCarta classe );
	public List<Carta> findByTipo( ETipoCarta tipo );
}