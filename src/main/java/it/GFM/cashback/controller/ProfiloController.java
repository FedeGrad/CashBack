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
import it.GFM.cashback.dto.InsertProfiloRequestDTO;
import it.GFM.cashback.dto.UpdatePagamentoRequestDTO;
import it.GFM.cashback.dto.UpdateProfiloRequestDTO;
import it.GFM.cashback.exception.ElementNotAvaible;
import it.GFM.cashback.exception.NotFoundException;
import it.GFM.cashback.exception.WrongInsertException;
import it.GFM.cashback.services.ProfiloService;

@Controller
public class ProfiloController {

	@Autowired
	ProfiloService profiloService;
	
	@Operation(summary = "Recupera tutti i Profili presenti nel sistema")
	@ApiResponse(responseCode = "200", description = "Profili trovati")
	@ApiResponse(responseCode = "404", description = "Nessun Profilo trovato")
//	@SecurityRequirement(name = "bearerAuth")
//	@PreAuthorize("isAuthenticated()")
	@GetMapping
	public ResponseEntity getAllProfili() {
		return ResponseEntity.ok(profiloService.findAllProfili());
	}
	
	
	@Operation(summary = "Recupera tutti i Profili presenti nel sistema passando il nome del profilo a parametro")
	@ApiResponse(responseCode = "200", description = "Profili trovati")
	@ApiResponse(responseCode = "404", description = "Nessun Profilo trovato")
//	@SecurityRequirement(name = "bearerAuth")
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/getallprofilibynome/{nome}")
	public ResponseEntity getAllProfiliByNome(@PathVariable ("nome") String nome ) throws NotFoundException {
		return ResponseEntity.ok(profiloService.findAllProfiliByNome(nome));
	}
	
	
	@Operation(summary = "Recupera tutti i Profili presenti nel sistema passando il cognome del profilo a parametro")
	@ApiResponse(responseCode = "200", description = "Profili trovati")
	@ApiResponse(responseCode = "404", description = "Nessun Profilo trovato")
//	@SecurityRequirement(name = "bearerAuth")
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/getallprofilibycognome/{cognome}")
	public ResponseEntity getAllProfiliByCognome(@PathVariable ("cognome") String cognome ) throws NotFoundException {
		return ResponseEntity.ok(profiloService.findAllProfiliByCognome(cognome));
	}
	
	
	
	@Operation(summary = "inserisce un Profilo nel sistema")
	@ApiResponse(responseCode = "200", description = "Profilo inserito correttamente")
	@ApiResponse(responseCode = "500", description = "ERRORE nell'inserimento")
	// @SecurityRequirement(name = "bearerAuth")
	// @PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity inserisciProfilo(@Valid @RequestBody InsertProfiloRequestDTO  dto)
			throws WrongInsertException,NotFoundException {
		profiloService.insertProfilo(dto);
		return ResponseEntity.ok("Profilo inserito");
	}
	
	
	
	@Operation(summary = "Modifica un Profilo nel sistema")
	@ApiResponse(responseCode = "200", description = "Profilo modificato")
	@ApiResponse(responseCode = "404", description = "Profilo non trovato")
	@ApiResponse(responseCode = "500", description = "Errore modifica")
	// @SecurityRequirement(name = "bearerAuth")
	// @PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity modificaProfilo(@Valid @RequestBody UpdateProfiloRequestDTO modificaDTO)
			throws NotFoundException, WrongInsertException, ElementNotAvaible {
		profiloService.updateProfilo(modificaDTO);
		return ResponseEntity.ok("Profilo modificato");
	}
	
	
	@Operation(summary = "Elimina un Profilo nel sistema")
	@ApiResponse(responseCode = "200", description = "Profilo eliminato")
	@ApiResponse(responseCode = "404", description = "Profilo non trovato")
	@ApiResponse(responseCode = "500", description = "Errore modifica")
	// @SecurityRequirement(name = "bearerAuth")
	// @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity eliminaProfilo(@PathVariable Long id) throws NotFoundException {
		profiloService.deleteProfilo(id);
		return ResponseEntity.ok("Profilo eliminato");
	}
	
}
