package it.GFM.cashback.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.GFM.cashback.dto.InsertPagamentoRequestDTO;
import it.GFM.cashback.dto.UpdatePagamentoRequestDTO;
import it.GFM.cashback.exception.ElementNotAvaible;
import it.GFM.cashback.exception.NotFoundException;
import it.GFM.cashback.exception.WrongInsertException;
import it.GFM.cashback.services.PagamentoService;

@Controller
public class PagamentoController {

	@Autowired
	PagamentoService pagamentoService;
	
	
	@Operation(summary = "Recupera tutti i pagamenti presenti nel sistema")
	@ApiResponse(responseCode = "200", description = "Pagamenti trovati")
	@ApiResponse(responseCode = "404", description = "Nessun Pagamento trovato")
//	@SecurityRequirement(name = "bearerAuth")
//	@PreAuthorize("isAuthenticated()")
	@GetMapping
	public ResponseEntity getAllPagamenti() {
		return ResponseEntity.ok(pagamentoService.findAllPagamenti());
	}
	
	
	@Operation(summary = "inserisce un Pagamento nel sistema", description = "")
	@ApiResponse(responseCode = "200", description = "Pagamento inserito correttamente")
	@ApiResponse(responseCode = "500", description = "ERRORE nell'inserimento")
	// @SecurityRequirement(name = "bearerAuth")
	// @PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity inserisciPagamento(@Valid @RequestBody InsertPagamentoRequestDTO  dto)
			throws WrongInsertException,NotFoundException {
		pagamentoService.insertPagamento(dto);
		return ResponseEntity.ok("Pagamento inserito");
	}
	
	
	
	@Operation(summary = "Modifica un Pagamento nel sistema", description = "")
	@ApiResponse(responseCode = "200", description = "Pagamento modificato")
	@ApiResponse(responseCode = "404", description = "Pagamento non trovato")
	@ApiResponse(responseCode = "500", description = "Errore modifica")
	// @SecurityRequirement(name = "bearerAuth")
	// @PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity modificaPagamento(@Valid @RequestBody UpdatePagamentoRequestDTO modificaDTO)
			throws NotFoundException, WrongInsertException, ElementNotAvaible {
		pagamentoService.updatePagamento(modificaDTO);
		return ResponseEntity.ok("Pagamento modificato");
	}
	
	
	
	@Operation(summary = "Elimina un Pagamento nel sistema", description = "")
	@ApiResponse(responseCode = "200", description = "Pagamento eliminato")
	@ApiResponse(responseCode = "404", description = "Pagamento non trovato")
	@ApiResponse(responseCode = "500", description = "Errore modifica")
	// @SecurityRequirement(name = "bearerAuth")
	// @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity eliminaPagamento(@PathVariable Long id) throws NotFoundException {
		pagamentoService.deletePagamento(id);
		return ResponseEntity.ok("Pagamento eliminato");
	}

	
	
}
