package it.GFM.cashback.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import it.GFM.cashback.model.EPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsertUtenteRequestDTO {
	
	private String username;
	private String password;
//	@Enumerated(EnumType.STRING)
//	private EPagamento tipo;
//	private String iban;
//	private String paypalUser;
//	private Long numeroCarta;

}
