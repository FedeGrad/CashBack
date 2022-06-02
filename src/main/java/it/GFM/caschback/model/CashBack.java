package it.GFM.caschback.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
// ppp cffsdfasdfsdfasdfsdf



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CashBack { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.NONE)
	private int id;
	
	@OneToMany(mappedBy = "cashBack",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	private List<Acquisto> acquisti;

	private BigDecimal prezzo;
	
	private float percentuale;
	
}
