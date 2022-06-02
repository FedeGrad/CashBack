package it.GFM.caschback.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsertUtenteRequestDTO {
	private String username;
	private String password;

}
