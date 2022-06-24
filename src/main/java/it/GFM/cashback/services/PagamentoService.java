
package it.GFM.cashback.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.GFM.cashback.dto.FindAllPagamentiResponseDTO;
import it.GFM.cashback.dto.InsertPagamentoRequestDTO;
import it.GFM.cashback.dto.TipoDTO;
import it.GFM.cashback.dto.UpdatePagamentoRequestDTO;
import it.GFM.cashback.exception.NotFoundException;
import it.GFM.cashback.model.EPagamento;
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

	public List<Pagamento> getByUsernameUtente(String username) throws NotFoundException {
		if (utenteRepository.existsByUsername(username)) {
			List<Pagamento> pagamenti = pagamentoRepository.findByUtente(username);
			return pagamenti;
		} else {
			throw new NotFoundException("Username non presente");
		}
	}

	public List<Pagamento> getByIban(String iban) throws NotFoundException {
		if (pagamentoRepository.existsByIban(iban)) {
			List<Pagamento> pagamenti = pagamentoRepository.findByIban(iban);
			return pagamenti;
		} else {
			throw new NotFoundException("Iban non presente");
		}
	}

	public List<Pagamento> getByNumeroCarta(Long numeroCarta) throws NotFoundException {
		if (pagamentoRepository.existsByNumeroCarta(numeroCarta)) {
			List<Pagamento> pagamenti = pagamentoRepository.findByNumeroCarta(numeroCarta);
			return pagamenti;
		} else {
			throw new NotFoundException("Iban non presente");
		}
	}

	public List<Pagamento> getByTipo(String tipo) throws NotFoundException {
		switch (tipo.toLowerCase()) {
			case "carta di credito":
				List<Pagamento> pagamentiCarta = pagamentoRepository.findByTipo(EPagamento.CARTA_DI_CREDITO);
				return pagamentiCarta;
			case "conto corrente":
			case "contocorrente":
				List<Pagamento> pagamentiConto = pagamentoRepository.findByTipo(EPagamento.CONTO_CORRENTE);
				return pagamentiConto;
			case "pay pal":
			case "paypal":
				List<Pagamento> pagamentiPayPal = pagamentoRepository.findByTipo(EPagamento.PAY_PAL);
				return pagamentiPayPal;
			default:
				throw new NotFoundException("Tipo non trovato");
		}
	}

	public void insertPagamento(InsertPagamentoRequestDTO dto) throws NotFoundException {
		Pagamento pagamento = new Pagamento();
		BeanUtils.copyProperties(dto, pagamento);
		if (utenteRepository.existsById(dto.getIdUtente())) {
			Utente utente = utenteRepository.findById(dto.getIdUtente()).get();
			pagamento.setUtente(utente);
			utente.getPagamenti().add(pagamento);
			pagamentoRepository.save(pagamento);

		} else {
			throw new NotFoundException("utente non trovato");
		}
	}

	public void updatePagamento(UpdatePagamentoRequestDTO dto) throws NotFoundException {
		if (pagamentoRepository.existsById(dto.getIdPagamento())) {
			Pagamento pagamento = pagamentoRepository.findById(dto.getIdPagamento()).get();
			BeanUtils.copyProperties(dto, pagamento);
			if (utenteRepository.existsById(dto.getIdUtente())) {
				Utente utente = utenteRepository.findById(dto.getIdUtente()).get();
				pagamento.setUtente(utente);
				utente.getPagamenti().add(pagamento);
				pagamentoRepository.save(pagamento);
			} else {
				throw new NotFoundException("utente non trovato");
			}
		} else {
			throw new NotFoundException("pagamento non trovato");
		}
	}

	public void deletePagamento(Long id) throws NotFoundException {
		if (pagamentoRepository.existsById(id)) {
			pagamentoRepository.deleteById(id);
		} else {
			throw new NotFoundException("Pagamento non trovato");
		}
	}

	public FindAllPagamentiResponseDTO findAllPagamenti() {
		FindAllPagamentiResponseDTO dto = new FindAllPagamentiResponseDTO();
		List<Pagamento> listaPagamento = pagamentoRepository.findAll();
		if (listaPagamento.size() > 0) {
			dto.setPagamentiTrovati(listaPagamento.size());
			dto.setElencoPagamenti(listaPagamento);
			return dto;
		} else {
			return null;
		}
	}

}
