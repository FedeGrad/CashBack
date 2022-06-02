package it.GFM.caschback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.GFM.caschback.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

	
	public Utente findByUsername(String username);
	
	public Boolean existsByUsername(String username);
	
}
