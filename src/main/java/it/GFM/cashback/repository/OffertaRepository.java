package it.GFM.cashback.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.GFM.cashback.model.Offerta;

public interface OffertaRepository extends JpaRepository<Offerta, Long> {
	
	public List<Offerta> findByDataInizioValidita(LocalDate inizioValidita);
	public List<Offerta> findByDataFineValidita(LocalDate fineValidita);

}
