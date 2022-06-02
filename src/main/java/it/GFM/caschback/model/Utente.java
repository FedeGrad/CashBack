package it.GFM.caschback.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.NONE)
	long id;
	private String username;
	private String password;
	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
	private List<Acquisto> acquisti = new ArrayList<Acquisto>();
	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
	private List<Profilo> profili = new ArrayList<Profilo>();
	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
	private List<Pagamento> pagamenti = new ArrayList<Pagamento>();
	@ManyToMany
	@JoinTable(name = "preferenze", 
	joinColumns = @JoinColumn(name = "id_utente"), 
	inverseJoinColumns = @JoinColumn(name = "id_offerta"))
	private List<Offerta> offerte = new ArrayList<Offerta>();

}
