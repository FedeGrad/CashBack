package it.GFM.cashback.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import it.GFM.cashback.dto.AcquistoDTO;
import it.GFM.cashback.dto.UpdateAcquistoRequestDTO;
import it.GFM.cashback.dto.DataDTO;
import it.GFM.cashback.exception.ElementNotAvaible;
import it.GFM.cashback.exception.NotFoundException;
import it.GFM.cashback.exception.WrongInsertException;
import it.GFM.cashback.repository.AcquistoRepository;
import it.GFM.cashback.services.AcquistoService;

@Controller
public class AcquistoController {

	@Autowired
	AcquistoService acquistoServ;
	@Autowired
	AcquistoRepository acquistoRepo;

	@Operation(summary = "Recupera tutti gli acquisti presenti nel sistema", description = "")
	@ApiResponse(responseCode = "200", description = "Acquisti trovati")
	@ApiResponse(responseCode = "404", description = "Nessun Acquisto trovato")
	// @SecurityRequirement(name = "bearerAuth")
	// @PreAuthorize("isAuthenticated()")
	@GetMapping
	public ResponseEntity getAllAcquisti() {
		return ResponseEntity.ok(acquistoServ.getAllAcquisti());
	}

	@Operation(summary = "Recupera tutti gli acquisti presenti nel sistema, paginati", description = "")
	@ApiResponse(responseCode = "200", description = "Acquisti trovati")
	@ApiResponse(responseCode = "404", description = "Nessun Acquisto trovato")
	// @SecurityRequirement(name = "bearerAuth")
	// @PreAuthorize("isAuthenticated()")
	@GetMapping("/getAcqusitiPage")
	public ResponseEntity getAllAcqusiti(Pageable page) {
		return ResponseEntity.ok(acquistoServ.getAcquistiPaginati(page));
	}

	@Operation(summary = "Recupera gli Acquisti per data di inserimento", description = "")
	@ApiResponse(responseCode = "200", description = "Acquisti trovati")
	@ApiResponse(responseCode = "404", description = "Nessun Acquisto trovato")
	// @SecurityRequirement(name = "bearerAuth")
	// @PreAuthorize("isAuthenticated()")
	@PostMapping("/getAcquistiByDataInserimento")
	public ResponseEntity getClienteByDataInserimento(@RequestBody DataDTO dto, Pageable page) {
		return ResponseEntity.ok(acquistoServ.getAcquistiByData(dto, page));
	}

	@Operation(summary = "Recupera gli Acquisti riferiti ad un Cliente", description = "")
	@ApiResponse(responseCode = "200", description = "Clienti trovati")
	@ApiResponse(responseCode = "404", description = "Nessun Cliente trovato")
	// @SecurityRequirement(name = "bearerAuth")
	// @PreAuthorize("isAuthenticated()")
	@GetMapping("/getAcquistiByUtente")
	public ResponseEntity getAcquistiByUtente(Long id, Pageable page) {
		return ResponseEntity.ok(acquistoServ.getAcquistiByUtente(id, page));
	}

	@Operation(summary = "inserisce un Acquisto nel sistema", description = "")
	@ApiResponse(responseCode = "200", description = "Acquisto inserito correttamente")
	@ApiResponse(responseCode = "500", description = "ERRORE nell'inserimento")
	// @SecurityRequirement(name = "bearerAuth")
	// @PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity inserisciAcquisto(@Valid @RequestBody AcquistoDTO dto)
			throws WrongInsertException, ElementNotAvaible, NotFoundException {
		acquistoServ.insertAcquisto(dto);
		return ResponseEntity.ok("Acquisto inserito");
	}

	@Operation(summary = "Modifica un Acquisto nel sistema", description = "")
	@ApiResponse(responseCode = "200", description = "Acquisto modificato")
	@ApiResponse(responseCode = "404", description = "Acquisto non trovato")
	@ApiResponse(responseCode = "500", description = "Errore modifica")
	// @SecurityRequirement(name = "bearerAuth")
	// @PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity modificaAcquisto(@Valid @RequestBody UpdateAcquistoRequestDTO modificaDTO)
			throws NotFoundException, WrongInsertException, ElementNotAvaible {
		acquistoServ.updateAcquisto(modificaDTO);
		return ResponseEntity.ok("Acquisto modificato");
	}

	@Operation(summary = "Elimina un Acquisto nel sistema", description = "")
	@ApiResponse(responseCode = "200", description = "Acquisto eliminato")
	@ApiResponse(responseCode = "404", description = "Acquisto non trovato")
	@ApiResponse(responseCode = "500", description = "Errore modifica")
	// @SecurityRequirement(name = "bearerAuth")
	// @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity eliminaAcquisto(@PathVariable Long id) throws NotFoundException {
		acquistoServ.deleteAcquisto(id);
		return ResponseEntity.ok("Acquisto eliminato");
	}

}
