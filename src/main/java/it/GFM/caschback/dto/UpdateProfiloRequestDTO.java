package it.GFM.caschback.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateProfiloRequestDTO {
	private Long idProfilo;
	private Long IdUtente;
	private String nome;
	private String cognome;
	private String email;
	private String indirizzo;
	private String citta;


}
