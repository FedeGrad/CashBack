package it.GFM.cashback.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import it.GFM.cashback.dto.AcquistoDTO;
import it.GFM.cashback.dto.AcquistoUpdateDTO;
import it.GFM.cashback.exception.ElementAlreadyPresentException;
import it.GFM.cashback.exception.ElementNotAvaible;
import it.GFM.cashback.exception.NotFoundException;
import it.GFM.cashback.model.Acquisto;
import it.GFM.cashback.model.CashBack;
import it.GFM.cashback.model.EStato;
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
	 * @throws NotFoundException 
	 * @throws ElementNotAvaible 
	 * @throws ElementAlreadyPresentException
	 */
	public void insertAcquisto(AcquistoDTO dto) throws NotFoundException, ElementNotAvaible{
		Acquisto acquisto = new Acquisto();
		BeanUtils.copyProperties(dto, acquisto);
		if(offertaRepo.existsById(dto.getIdOfferta())) {
			Offerta offerta = offertaRepo.findById(dto.getIdOfferta()).get();
			if(offerta.getStatoOfferta().equals(EStato.DISPONIBILE)) {
				acquisto.getOfferte().add(offerta);
				offerta.setAcquisto(acquisto);
			}
			else {
				throw new ElementNotAvaible("Offerta non disponibile");
			}
		}
		else {
			throw new NotFoundException("Offerta non esistente");
		}
		if(cashRepo.existsById(dto.getIdCashback())) {
			CashBack cashBack = cashRepo.findById(dto.getIdCashback()).get();
			acquisto.setCashBack(cashBack);
			cashBack.getAcquisti().add(acquisto);
		}
		else {
			throw new NotFoundException("Cash back non esistente");
		}
		if(utenteRepo.existsById(dto.getIdUtente())) {
			Utente utente = utenteRepo.findById(dto.getIdUtente()).get();
			acquisto.setUtente(utente);
			utente.getAcquisti().add(acquisto);
		}
		else {
			throw new NotFoundException("utente non esistente");
		}

		acquistoRepo.save(acquisto);
		log.info("L'acquisto è stato salvato");
	}

	/**
	 * Modifica un acquisto
	 * 
	 * @param dto
	 * @throws ElementNotAvaible 
	 * @throws NotFoundException 
	 */
	public void updateAcquisto(AcquistoUpdateDTO dto) throws ElementNotAvaible, NotFoundException {
		if (acquistoRepo.existsById(dto.getIdAcquisto())) {
			Acquisto acquisto = acquistoRepo.findById(dto.getIdAcquisto()).get();
			BeanUtils.copyProperties(dto, acquisto);
			if(offertaRepo.existsById(dto.getIdOfferta())) {
				Offerta offerta = offertaRepo.findById(dto.getIdOfferta()).get();
				if(offerta.getStatoOfferta().equals("DISPONIBILE")) {
					acquisto.getOfferte().add(offerta);
					offerta.setAcquisto(acquisto);
				}
				else {
					throw new ElementNotAvaible("Offerta non disponibile");
				}
			}
			else {
				throw new NotFoundException("Offerta non esistente");
			}
			if(cashRepo.existsById(dto.getIdCashback())) {
				CashBack cashBack = cashRepo.findById(dto.getIdCashback()).get();
				acquisto.setCashBack(cashBack);
				cashBack.getAcquisti().add(acquisto);
			}
			else {
				throw new NotFoundException("Cash back non esistente");
			}
			if(utenteRepo.existsById(dto.getIdUtente())) {
				Utente utente = utenteRepo.findById(dto.getIdUtente()).get();
				acquisto.setUtente(utente);
				utente.getAcquisti().add(acquisto);
			}
			else {
				throw new NotFoundException("utente non esistente");
			}

			acquistoRepo.save(acquisto);
			}
	}

	/**
	 * Elimina un acquisto
	 * 
	 * @param id
	 * @throws NotFoundException 
	 */
	public void deleteAcquisto(Long id) throws NotFoundException {
		if (acquistoRepo.existsById(id)) {
			acquistoRepo.deleteById(id);
			log.info("L'acquisto id n°" + id + " è stato eliminato");
		} else {
			throw new NotFoundException("L'acquisto id n°" + id + " non presente nel sistema");
		}
	}

}
