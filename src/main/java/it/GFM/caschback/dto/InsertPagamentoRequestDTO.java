package it.GFM.caschback.dto;

import it.GFM.caschback.model.EPagamento;
import it.GFM.caschback.model.Utente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsertPagamentoRequestDTO {

	private Long idUtente;
	private EPagamento tipo;
	private String iban;
	private String paypalUser;
	private Long numeroCarta;
}
