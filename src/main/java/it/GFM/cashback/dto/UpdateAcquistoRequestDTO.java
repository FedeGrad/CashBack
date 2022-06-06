package it.GFM.cashback.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAcquistoRequestDTO {
	
	private Long idAcquisto;
	private boolean offerta = false;
	private Long idOfferta;
	private Long idCashback;
	private Long idUtente;

}
