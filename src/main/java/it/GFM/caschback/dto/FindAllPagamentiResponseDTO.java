package it.GFM.caschback.dto;

import java.util.List;

import it.GFM.caschback.model.Pagamento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindAllPagamentiResponseDTO {
	private int pagamentiTrovati;
	private List<Pagamento> elencoPagamenti;

}
