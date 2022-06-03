package it.GFM.cashback.dto;

import java.util.List;

import it.GFM.cashback.model.Utente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindAllUtentiResponseDTO {
	private int utentiTrovati;
	private List<Utente> elencoUtenti;

}
