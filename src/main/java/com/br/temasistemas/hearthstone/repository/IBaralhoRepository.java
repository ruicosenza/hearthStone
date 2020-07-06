package com.br.temasistemas.hearthstone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.temasistemas.hearthstone.enums.EClasseCarta;
import com.br.temasistemas.hearthstone.model.Baralho;

public interface IBaralhoRepository extends JpaRepository<Baralho, Integer> {

	public Optional<Baralho> findByNome( String nome );
	public List<Baralho> findByClasse( EClasseCarta classe );
}