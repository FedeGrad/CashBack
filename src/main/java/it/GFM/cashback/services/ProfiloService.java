package it.GFM.cashback.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.GFM.cashback.dto.FindAllProfiliResponseDTO;
import it.GFM.cashback.dto.InsertProfiloRequestDTO;
import it.GFM.cashback.dto.UpdateProfiloRequestDTO;
import it.GFM.cashback.exception.NotFoundException;
import it.GFM.cashback.model.Profilo;
import it.GFM.cashback.model.Utente;
import it.GFM.cashback.repository.ProfiloRepository;
import it.GFM.cashback.repository.UtenteRepository;

@Service
public class ProfiloService {

	@Autowired
	ProfiloRepository profiloRepository;
	@Autowired
	UtenteRepository utenteRepository;

	public void insertProfilo(InsertProfiloRequestDTO dto) throws NotFoundException {
		Profilo profilo = new Profilo();
		BeanUtils.copyProperties(dto, profilo);
		if (utenteRepository.existsById(dto.getIdUtente())) {
			Utente utente = utenteRepository.findById(dto.getIdUtente()).get();
			profilo.setUtente(utente);
			utente.getProfili().add(profilo);
			profiloRepository.save(profilo);
		} else {
			throw new NotFoundException("utente non trovato");
		}
	}

	public void updateProfilo(UpdateProfiloRequestDTO dto) throws NotFoundException {
		if (profiloRepository.existsById(dto.getIdProfilo())) {
			Profilo profilo = profiloRepository.findById(dto.getIdProfilo()).get();
			BeanUtils.copyProperties(dto, profilo);
			if (utenteRepository.existsById(dto.getIdUtente())) {
				Utente utente = utenteRepository.findById(dto.getIdUtente()).get();
				profilo.setUtente(utente);
				utente.getProfili().add(profilo);
				profiloRepository.save(profilo);
			} else {
				throw new NotFoundException("utente non trovato");
			}
		} else {
			throw new NotFoundException("Profilo non trovato");
		}
	}

	public void deleteProfilo(Long id) throws NotFoundException {
		if (profiloRepository.existsById(id)) {
			profiloRepository.deleteById(id);
		} else {
			throw new NotFoundException("Profilo non trovato");
		}
	}

	public FindAllProfiliResponseDTO findAllProfili() {
		FindAllProfiliResponseDTO dto = new FindAllProfiliResponseDTO();
		List<Profilo> listaProfilo = (List) profiloRepository.findAll();
		if (listaProfilo.size() > 0) {
			dto.setProfiliTrovati(listaProfilo.size());
			dto.setElencoProfili(listaProfilo);
			return dto;
		} else {
			return null;
		}
	}

	public List<Profilo> findAllProfiliByNome(String nome) throws NotFoundException {
		if (profiloRepository.existsByNomeContaining(nome)) {
			List<Profilo> listaProfili = (List) profiloRepository.findByNomeContaining(nome);
			return listaProfili;
		} else {
			throw new NotFoundException("nessun profilo trovato");
		}
	}

	public List<Profilo> findAllProfiliByCognome(String cognome) throws NotFoundException {
		if (profiloRepository.existsByCognomeContaining(cognome)) {
			List<Profilo> listaProfili = (List) profiloRepository.findByCognomeContaining(cognome);
			return listaProfili;
		} else {
			throw new NotFoundException("nessun profilo trovato");
		}
	}

}
