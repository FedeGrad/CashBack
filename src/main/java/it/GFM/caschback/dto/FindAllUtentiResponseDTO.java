package it.GFM.caschback.dto;

import java.util.List;

import it.GFM.caschback.model.Utente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindAllUtentiResponseDTO {
	private int utentiTrovati;
	private List<Utente> elencoUtenti;

}
