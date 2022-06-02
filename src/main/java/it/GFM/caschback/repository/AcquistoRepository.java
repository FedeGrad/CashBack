package it.GFM.caschback.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.GFM.caschback.model.Acquisto;
import it.GFM.caschback.model.Offerta;
import it.GFM.caschback.model.Utente;

public interface AcquistoRepository extends JpaRepository<Acquisto, Long> {
	
	@Query(value = "", nativeQuery = true)
	public List<Acquisto> findByUtente(Utente utente);
	public List<Acquisto> findByDataAcquisto(LocalDate dataAcquisto);
	public List<Acquisto> findByOfferta(List<Offerta> offerta);
	
}
