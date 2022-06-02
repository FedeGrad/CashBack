package it.GFM.caschback.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Preferenza {
	//ok3
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.NONE)
	private Long id;
	private List<String> username = new ArrayList<String>();
	private List<String> viaggi = new ArrayList<String>();
	private List<String> casa = new ArrayList<String>();
	private List<String> libri = new ArrayList<String>();
	private List<String> cosmetici = new ArrayList<String>();
	private List<String> ristoranti = new ArrayList<String>();
	private List<String> alberghi = new ArrayList<String>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username_utente")
	private Utente username_utente;
}
