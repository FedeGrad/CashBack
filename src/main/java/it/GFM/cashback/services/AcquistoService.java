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
import it.GFM.cashback.dto.AcquistoUpdateDTO;
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
	OffertaRepository offertaRepo;
	@Autowired
	UtenteRepository utenteRepo;
	@Autowired
	CashBackRepository cashRepo;

	/**
	 * Recupera tutti gli acquisti
	 * 
	 * @deprecated
	 * @return
	 */
	public List<Acquisto> getAllAcquisti() {
		return (List<Acquisto>) acquistoRepo.findAll();
	}

	/**
	 * Recupera tutti gli acqusiti, paginati
	 * 
	 * @param page
	 * @return
	 */
	public Page<Acquisto> getAcquistiPaginati(Pageable page) {
		return (Page<Acquisto>) acquistoRepo.findAll(page);
	}
	
	/**
	 * Recupera tutti gli acqusiti, dalla data
	 * 
	 * @param page
	 * @return
	 */
	public Page<Acquisto> getAcquistiByData(LocalDate dataAcquisto, Pageable page) {
		return (Page<Acquisto>) acquistoRepo.findByDataAcquisto(dataAcquisto, page);
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
	 * Inserisce un acquisto
	 * 
	 * @param dto
	 * @throws ElementAlreadyPresentException
	 */
	public void insertAcquisto(AcquistoDTO dto){
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
	 * Modifica un acquisto
	 * 
	 * @param dto
	 */
	public void updateAcquisto(AcquistoUpdateDTO dto) {
		if (acquistoRepo.existsById(dto.getIdAcquisto())) {
			Acquisto acquisto = acquistoRepo.findById(dto.getIdAcquisto()).get();
			if(dto.isOfferta()==true) {
				Offerta offerta = offertaRepo.findById(dto.getIdOfferta()).get();
				acquisto.getOfferte().add(offerta);
				offerta.setAcquisto(acquisto);
			}
			if(dto.getIdCashback() != null) {
				CashBack cashBack = cashRepo.findById(dto.getIdCashback()).get();
				acquisto.setCashBack(cashBack);
				cashBack.getAcquisti().add(acquisto);
			}
			if(dto.getIdUtente() != null) {
				Utente utente = utenteRepo.findById(dto.getIdUtente()).get();
				acquisto.setUtente(utente);
				utente.getAcquisti().add(acquisto);
			}
			acquistoRepo.save(acquisto);
			log.info("L'acquisto è stato modificato");
		} else {
			throw new NotFoundException("L'acquisto id n°" + dto.getIdAcquisto() + " non è presente nel sistema");
		}
	}

	/**
	 * Elimina un acquisto
	 * 
	 * @param id
	 */
	public void deleteAcquisto(Long id) {
		if (acquistoRepo.existsById(id)) {
			acquistoRepo.deleteById(id);
			log.info("L'acquisto id n°" + id + " è stato eliminato");
		} else {
			throw new NotFoundException("L'acquisto id n°" + id + " non presente nel sistema");
		}
	}

}
