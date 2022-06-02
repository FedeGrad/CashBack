package it.GFM.caschback.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUtenteRequestDTO {
	private Long id;
	private String username;
	private String password;
}
