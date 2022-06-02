package it.GFM.caschback.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import it.GFM.caschback.dto.FindAllUtentiResponseDTO;
import it.GFM.caschback.dto.InsertUtenteRequestDTO;
import it.GFM.caschback.dto.UpdateUtenteRequestDTO;
import it.GFM.caschback.model.Utente;
import it.GFM.caschback.repository.UtenteRepository;
import it.GFM.cashback.error.NotFoundException;

@Service
public class UtenteService {

	@Autowired
	UtenteRepository utenteRepository;

	public void insertUtente(InsertUtenteRequestDTO dto) {
		Utente utente = new Utente();
		BeanUtils.copyProperties(dto, utente);
		utente.setPassword(BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt()));
		utenteRepository.save(utente);
	}

	public void updateUtente(UpdateUtenteRequestDTO dto) throws NotFoundException {
		if (utenteRepository.existsById(dto.getId())) {
			Utente utente = utenteRepository.findById(dto.getId()).get();
			BeanUtils.copyProperties(dto, utente);
			utente.setPassword(BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt()));
			utenteRepository.save(utente);
		} else {
			throw new NotFoundException("utente non esistente");
		}

	}

	public void deleteUtente(Long id) throws NotFoundException {
		if (utenteRepository.existsById(id)) {
			utenteRepository.deleteById(id);
		} else {
			throw new NotFoundException("utente non esistente");
		}
	}

	public FindAllUtentiResponseDTO findAllUtenti() {
		FindAllUtentiResponseDTO dto = new FindAllUtentiResponseDTO();
		List<Utente> listaUtente = utenteRepository.findAll();
		if (listaUtente.size() > 0) {
			dto.setUtentiTrovati(listaUtente.size());
			dto.setElencoUtenti(listaUtente);
			return dto;
		} else {
			return null;
		}
	}

	public Utente findUtenteUsername(String username) throws NotFoundException {
		if (utenteRepository.existsByUsername(username)) {
			Utente utente = utenteRepository.findByUsername(username);
			return utente;
		} else {
			throw new NotFoundException("utente non trovato");
		}
	}

	public Utente findById(Long id) throws NotFoundException {
		if (utenteRepository.existsById(id)) {
			Utente utente = utenteRepository.findById(id).get();
			return utente;
		} else {
			throw new NotFoundException("utente non trovato");
		}
	}

}
