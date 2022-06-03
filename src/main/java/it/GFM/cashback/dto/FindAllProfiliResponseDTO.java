package it.GFM.cashback.dto;

import java.util.List;

import it.GFM.cashback.model.Profilo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindAllProfiliResponseDTO {
	private int profiliTrovati;
	private List<Profilo> elencoProfili;

}
