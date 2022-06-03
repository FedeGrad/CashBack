package it.GFM.cashback.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.GFM.cashback.model.Acquisto;
import it.GFM.cashback.model.Offerta;
import it.GFM.cashback.model.Utente;

public interface AcquistoRepository extends JpaRepository<Acquisto, Long> {
	
	@Query(value = "SELECT * FROM Acquisto JOIN Utente ON Acquisto.id_utente = Utente.id"
			+ "WHERE Utente.id = ?1", nativeQuery = true)
	public List<Acquisto> findByUtente(Long idUtente);
	public Page<Acquisto> findByDataAcquisto(LocalDate dataAcquisto, Pageable page);
	
}
