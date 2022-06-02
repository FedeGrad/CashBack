package it.GFM.caschback.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Acquisto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "IdAcquisto",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	private List <Offerta> idOfferta;
	
	private LocalDate dataAcquisto;
	@ManyToOne
	@JoinColumn(name = "id_utente")
	private Utente IdUtente;
	
	

}
