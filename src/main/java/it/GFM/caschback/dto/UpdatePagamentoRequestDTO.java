package it.GFM.caschback.dto;

import it.GFM.caschback.model.EPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePagamentoRequestDTO {
	private Long idPagamento;
	private Long idUtente;
	private EPagamento tipo;
	private String iban;
	private String paypalUser;
	private Long numeroCarta;

}
