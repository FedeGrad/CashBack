package it.GFM.cashback.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import it.GFM.cashback.dto.FindAllUtentiResponseDTO;
import it.GFM.cashback.dto.InsertUtenteRequestDTO;
import it.GFM.cashback.dto.UpdateUtenteRequestDTO;
import it.GFM.cashback.exception.ElementAlreadyPresentException;
import it.GFM.cashback.exception.NotFoundException;
import it.GFM.cashback.model.Utente;
import it.GFM.cashback.repository.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	UtenteRepository utenteRepository;

	public void insertUtente(InsertUtenteRequestDTO dto) throws ElementAlreadyPresentException {
		if(!utenteRepository.existsByUsername(dto.getUsername())) {
			Utente utente = new Utente();
			BeanUtils.copyProperties(dto, utente);
			utente.setPassword(BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt()));
			utenteRepository.save(utente);
		} else {
			throw new ElementAlreadyPresentException("Utente gia esistente");
		}
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

	public FindAllUtentiResponseDTO findAllUtenti() throws NotFoundException {
		FindAllUtentiResponseDTO dto = new FindAllUtentiResponseDTO();
		List<Utente> listaUtente = utenteRepository.findAll();
		if (listaUtente.size() > 0) {
			dto.setUtentiTrovati(listaUtente.size());
			dto.setElencoUtenti(listaUtente);
			return dto;
		} else {
			throw new NotFoundException("utente non trovato");
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
