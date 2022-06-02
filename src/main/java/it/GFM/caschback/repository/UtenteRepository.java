package it.GFM.caschback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.GFM.caschback.model.Acquisto;
import it.GFM.caschback.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
	
	@Query(value = "", nativeQuery = true)
	public List<Utente> findByAcquisti(String acquisto);
	public Utente findByUsername(String username);
	public boolean existsByUsername(String username);
	public Long deleteByUsername(String username);
	
}
