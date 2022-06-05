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
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.GFM.cashback.dto.InsertUtenteRequestDTO;
import it.GFM.cashback.dto.UpdateUtenteRequestDTO;
import it.GFM.cashback.exception.ElementAlreadyPresentException;
import it.GFM.cashback.exception.ElementNotAvaible;
import it.GFM.cashback.exception.NotFoundException;
import it.GFM.cashback.exception.WrongInsertException;
import it.GFM.cashback.services.UtenteService;

@Controller
@RequestMapping("/utente")
public class UtenteController {

	@Autowired
	UtenteService utenteService;
	
	
	@Operation(summary = "Recupera tutti gli Utenti presenti nel sistema")
	@ApiResponse(responseCode = "200", description = "Utenti trovati")
	@ApiResponse(responseCode = "404", description = "Nessun Utente trovato")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/")
	public ResponseEntity getAllUtenti() throws NotFoundException {
		return ResponseEntity.ok(utenteService.findAllUtenti());
	}
	
	
	@Operation(summary = "Recupera tutti gli Utenti presenti nel sistema passando l'username dell'Utente a parametro")
	@ApiResponse(responseCode = "200", description = "Utenti trovati")
	@ApiResponse(responseCode = "404", description = "Nessun Utente trovato")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/getallutentibyusername/{username}")
	public ResponseEntity getAllUtentiByNomeUsername(@PathVariable ("username") String username ) throws NotFoundException {
		return ResponseEntity.ok(utenteService.findUtenteUsername(username));
	}
	
	
	@Operation(summary = "Recupera tutti gli Utenti presenti nel sistema passando l'ID dell'Utente a parametro")
	@ApiResponse(responseCode = "200", description = "Utenti trovati")
	@ApiResponse(responseCode = "404", description = "Nessun Utente trovato")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/getallutentibyid/{id}")
	public ResponseEntity getAllUtentiById(@PathVariable ("id") Long id ) throws NotFoundException {
		return ResponseEntity.ok(utenteService.findById(id));
	}
	
	@Operation(summary = "inserisce un Utente nel sistema")
	@ApiResponse(responseCode = "200", description = "Utente inserito correttamente")
	@ApiResponse(responseCode = "500", description = "ERRORE nell'inserimento")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public ResponseEntity inserisciUtente(@Valid @RequestBody InsertUtenteRequestDTO  dto)
			throws WrongInsertException,NotFoundException, ElementAlreadyPresentException {
		utenteService.insertUtente(dto);
		return ResponseEntity.ok("Utente inserito");
	}
	
	
	@Operation(summary = "Modifica un Utente nel sistema")
	@ApiResponse(responseCode = "200", description = "Utente modificato")
	@ApiResponse(responseCode = "404", description = "Utente non trovato")
	@ApiResponse(responseCode = "500", description = "Errore modifica")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/")
	public ResponseEntity modificaUtente(@Valid @RequestBody UpdateUtenteRequestDTO modificaDTO)
			throws NotFoundException, WrongInsertException, ElementNotAvaible {
		utenteService.updateUtente(modificaDTO);
		return ResponseEntity.ok("Utente modificato");
	}
	
	

	@Operation(summary = "Elimina un Utente nel sistema")
	@ApiResponse(responseCode = "200", description = "Utente eliminato")
	@ApiResponse(responseCode = "404", description = "Utente non trovato")
	@ApiResponse(responseCode = "500", description = "Errore modifica")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity eliminaUtente(@PathVariable Long id) throws NotFoundException {
		utenteService.deleteUtente(id);
		return ResponseEntity.ok("Utente eliminato");
	}
	
}
