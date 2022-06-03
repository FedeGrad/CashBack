package it.GFM.cashback.dto;

import it.GFM.cashback.model.EPagamento;
import it.GFM.cashback.model.Utente;
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
