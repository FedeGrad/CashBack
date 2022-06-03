package it.GFM.cashback.dto;

import java.util.List;

import it.GFM.cashback.model.Pagamento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindAllPagamentiResponseDTO {
	private int pagamentiTrovati;
	private List<Pagamento> elencoPagamenti;

}
