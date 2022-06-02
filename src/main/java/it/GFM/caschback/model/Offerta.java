package it.GFM.caschback.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offerta {
	//boh
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private ETipo tipo;
	private double costo;
	private LocalDate dataInizioValidita;
	private LocalDate dataFineValidita;
	private String cashBack;
	@ManyToOne 
	@JoinColumn(name = "id_acquisto")
	private Acquisto IdAcquisto;

}
