package it.GFM.cashback.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.GFM.cashback.dto.FindAllPagamentiResponseDTO;
import it.GFM.cashback.dto.InsertPagamentoRequestDTO;
import it.GFM.cashback.dto.UpdatePagamentoRequestDTO;
import it.GFM.cashback.exception.NotFoundException;
import it.GFM.cashback.model.Pagamento;
import it.GFM.cashback.model.Utente;
import it.GFM.cashback.repository.PagamentoRepository;
import it.GFM.cashback.repository.UtenteRepository;


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
	
	public void deletePagamento(Long id) throws NotFoundException {
		if(pagamentoRepository.existsById(id)) {
			pagamentoRepository.deleteById(id);
		}
		else {
			throw new NotFoundException("Pagamento non trovato");
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


