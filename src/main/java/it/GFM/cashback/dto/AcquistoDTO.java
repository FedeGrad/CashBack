package it.GFM.cashback.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcquistoDTO {
	
	static final String DATE_PATTERN ="dd/MM/yyyy";

	
	@Schema(example = "20/02/2000", type = "string")	
	@JsonFormat(pattern = DATE_PATTERN)
	private LocalDate dataAcquisto;
	private Long idOfferta;
	private Long idCashback;
	private Long idUtente;


}
