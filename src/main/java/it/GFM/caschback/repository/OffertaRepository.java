package it.GFM.caschback.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.GFM.caschback.model.Offerta;

public interface OffertaRepository extends JpaRepository<Offerta, Long> {
	
	public List<Offerta> findByDataInizioValidita(LocalDate inizioValidita);
	public List<Offerta> findByDataFineValidita(LocalDate fineValidita);

}
