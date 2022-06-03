package it.GFM.cashback.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import it.GFM.cashback.dto.AcquistoDTO;
import it.GFM.cashback.exception.ElementAlreadyPresentException;
import it.GFM.cashback.model.Acquisto;
import it.GFM.cashback.model.CashBack;
import it.GFM.cashback.model.Offerta;
import it.GFM.cashback.model.Utente;
import it.GFM.cashback.repository.AcquistoRepository;
import it.GFM.cashback.repository.CashBackRepository;
import it.GFM.cashback.repository.OffertaRepository;
import it.GFM.cashback.repository.UtenteRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AcquistoService {
	
	@Autowired
	AcquistoRepository acquistoRepo;
	@Autowired
	AcquistoService acquistoServ;
	@Autowired
	OffertaRepository offertaRepo;
	@Autowired
	UtenteRepository utenteRepo;
	@Autowired
	CashBackRepository cashRepo;

	/**
	 * Recupera tutti gli Indirizzi Legali
	 * 
	 * @deprecated
	 * @return
	 */
	public List<Acquisto> getAllAcquisti() {
		return (List<Acquisto>) acquistoRepo.findAll();
	}

	/**
	 * Recupera tutti gli Indirizzi Legali, paginati
	 * 
	 * @param page
	 * @return
	 */
	public Page<Acquisto> geAcquistiPaginati(Pageable page) {
		return (Page<Acquisto>) acquistoRepo.findAll(page);
	}
	
	/**
	 * 
	 * @param idUtente
	 * @param page
	 * @return
	 */
	public Page<Acquisto> getAcquistiByUtente(Long idUtente, Pageable page) {
		return (Page<Acquisto>) acquistoRepo.findByUtente(idUtente);
	}

	/**
	 * Inserisce un Indirizzo Legale
	 * 
	 * @param dto
	 * @throws ElementAlreadyPresentException
	 */
	public void inserisciAcquisto(AcquistoDTO dto){
			Acquisto acquisto = new Acquisto();
			acquisto.setDataAcquisto(LocalDate.now());
			if(dto.isOfferta()==true) {
				Offerta offerta = offertaRepo.findById(dto.getIdOfferta()).get();
				acquisto.getOfferte().add(offerta);
				offerta.setAcquisto(acquisto);
			}
			CashBack cashBack = cashRepo.findById(dto.getIdCashback()).get();
			acquisto.setCashBack(cashBack);
			cashBack.getAcquisti().add(acquisto);
			Utente utente = utenteRepo.findById(dto.getIdUtente()).get();
			acquisto.setUtente(utente);
			utente.getAcquisti().add(acquisto);
			acquistoRepo.save(acquisto);
			log.info("L'acquisto è stato salvato");
	}

	/**
	 * Modifica un Indirizzo Legale
	 * 
	 * @param dto
	 */
	public void modificaIndirizzoLegale(IndirizzoModificaDTO dto) {
		if (indiLegRepo.existsById(dto.getIdIndirizzo())) {
			IndirizzoLegale indirizzo = indiLegRepo.findById(dto.getIdIndirizzo()).get();
			BeanUtils.copyProperties(dto, indirizzo);
			Comune comuneTrovato = comuneServ.associaComune(dto.getLocalita());
			indirizzo.setComune(comuneTrovato);
			comuneTrovato.getIndirizziLegali().add(indirizzo);
			indiLegRepo.save(indirizzo);
			log.info("l'indirizzo Legale è stato modificato");
		} else {
			throw new NotFoundException("L'Indirizzo Legale n°" + dto.getIdIndirizzo() + " non è presente nel sistema");
		}
	}

	/**
	 * Elimina un Indirizzo Legale
	 * 
	 * @param id
	 */
	public void eliminaIndirizzoLegale(Long id) {
		if (indiLegRepo.existsById(id)) {
			indiLegRepo.deleteById(id);
			log.info("L'indirizzo Legale n°" + id + " è stato eliminato");
		} else {
			throw new NotFoundException("L'indirizzo Legale n°" + id + " non presente nel sistema");
		}
	}

	

}
