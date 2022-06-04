package it.GFM.cashback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.GFM.cashback.model.Acquisto;
import it.GFM.cashback.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
	
	@Query(value = "SELECT * Utente JOIN Acquisto ON Utente.id = Acquisto.id_utente "
			+ "WHERE Acquisto.id = ?1", nativeQuery = true)
	public List<Utente> findByAcquisti(Long idAcquisto);
	public Utente findByUsername(String username);
	public boolean existsByUsername(String username);
	public Long deleteByUsername(String username);
	
}
