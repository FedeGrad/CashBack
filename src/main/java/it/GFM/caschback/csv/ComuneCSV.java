package it.GFM.caschback.csv;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComuneCSV {

	@JsonProperty("Comune")
	private String nome;
	@JsonProperty("Provincia")
	private String siglaProvincia;
	@JsonProperty("CAP")
	private String cap;
}
