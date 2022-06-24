package it.GFM.cashback.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import it.GFM.cashback.model.EPagamento;
import it.GFM.cashback.model.ETipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoDTO {
	
	@Enumerated(EnumType.STRING)
	private EPagamento tipo;

}
