package it.GFM.caschback.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.GFM.caschback.dto.FindAllPagamentiResponseDTO;
import it.GFM.caschback.dto.InsertPagamentoRequestDTO;
import it.GFM.caschback.dto.UpdatePagamentoRequestDTO;
import it.GFM.caschback.model.Pagamento;
import it.GFM.caschback.model.Utente;
import it.GFM.caschback.repository.PagamentoRepository;
import it.GFM.caschback.repository.UtenteRepository;
import it.GFM.cashback.error.NotFoundException;


@Service
public class PagamentoService {
	@Autowired
	PagamentoRepository pagamentoRepository;
	@Autowired
	UtenteRepository utenteRepository;

	public void insertPagamento(InsertPagamentoRequestDTO dto) throws NotFoundException {
		Pagamento pagamento = new Pagamento();
		BeanUtils.copyProperties(dto, pagamento);
		if(utenteRepository.existsById(dto.getIdUtente())) {
			Utente utente = utenteRepository.findById(dto.getIdUtente()).get();
			pagamento.setUtente(utente);
			utente.getPagamenti().add(pagamento);
			pagamentoRepository.save(pagamento);

		}
		else {
			throw new NotFoundException("utente non trovato");
		}
	}

	public void updatePagamento(UpdatePagamentoRequestDTO dto) throws NotFoundException {
		if(pagamentoRepository.existsById(dto.getIdPagamento())) {
			Pagamento pagamento = pagamentoRepository.findById(dto.getIdPagamento()).get();
			BeanUtils.copyProperties(dto, pagamento);
			if(utenteRepository.existsById(dto.getIdUtente())) {
				Utente utente = utenteRepository.findById(dto.getIdUtente()).get();
				pagamento.setUtente(utente);
				utente.getPagamenti().add(pagamento);
				pagamentoRepository.save(pagamento);
			}
			else {
				throw new NotFoundException("utente non trovato");
			}
		}
		else {
			throw new NotFoundException("pagamento non trovato");
		}
	}
	
	public void deletePagamento(Long id) {
		if(pagamentoRepository.existsById(id)) {
			pagamentoRepository.deleteById(id);
		}
	}
	
	
	public  FindAllPagamentiResponseDTO findAllPagamenti() {
		FindAllPagamentiResponseDTO dto = new FindAllPagamentiResponseDTO();
		List <Pagamento> listaPagamento = pagamentoRepository.findAll();
		if(listaPagamento.size()>0) {
			dto.setPagamentiTrovati(listaPagamento.size());
			dto.setElencoPagamenti(listaPagamento);
			return dto;
		}
		else {
			return null;
		}	
	}
	
	}


