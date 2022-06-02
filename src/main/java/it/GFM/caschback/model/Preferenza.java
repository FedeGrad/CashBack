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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Preferenza {
	//ok
	//ok
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private List<ETipo> viaggi = new ArrayList<ETipo>();
	private List<ETipo> casa = new ArrayList<ETipo>();
	private List<ETipo> libri = new ArrayList<ETipo>();
	private List<ETipo> cosmetici = new ArrayList<ETipo>();
	private List<ETipo> ristoranti = new ArrayList<ETipo>();
	private List<ETipo> alberghi = new ArrayList<ETipo>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username_utente")
	private Utente username_utente;
}
