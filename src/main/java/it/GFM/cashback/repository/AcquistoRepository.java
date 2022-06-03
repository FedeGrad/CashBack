package it.GFM.cashback.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.GFM.cashback.model.Acquisto;
import it.GFM.cashback.model.Offerta;
import it.GFM.cashback.model.Utente;

public interface AcquistoRepository extends JpaRepository<Acquisto, Long> {
	
	@Query(value = "", nativeQuery = true)
	public List<Acquisto> findByUtente(Long idUtente);
	public List<Acquisto> findByDataAcquisto(LocalDate dataAcquisto);
	public List<Acquisto> findByOfferta(List<Offerta> offerta);
	
}
