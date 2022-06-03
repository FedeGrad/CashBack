package it.GFM.cashback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.GFM.cashback.model.Profilo;

public interface ProfiloRepository extends JpaRepository<Profilo, Long> {

	public Profilo findByEmail(String email);
	public Profilo existsByEmail(String email);
	public List<Profilo> findByNome(String nome);
	public List<Profilo> findByNomeContaining(String nome);
	public List<Profilo> findByCognome(String cognome);
	public List<Profilo> findByCognomeContaining(String cognome);
	public boolean existsByNome(String nome);
	public boolean existsByNomeContaining(String nome);
	public boolean existsByCognome(String cognome);
	public boolean existsByCognomeContaining(String cognome);
	public List<Profilo> findByUtente(String username);

}
