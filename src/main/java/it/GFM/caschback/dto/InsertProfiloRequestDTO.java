package it.GFM.caschback.dto;

import it.GFM.caschback.model.Utente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsertProfiloRequestDTO {
	private Long IdUtente;
	private String nome;
	private String cognome;
	private String email;
	private String indirizzo;
	private String citta;

}
