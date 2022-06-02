package it.GFM.caschback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.GFM.caschback.model.Profilo;

public interface ProfiloRepository extends JpaRepository<Profilo, Long> {
	
	public List<Profilo> findByNomeContaining(String nome);
	public List<Profilo> findByCognomeContaining(String cognome);
	public Boolean existsByNomeContaining(String nome);
	public Boolean existsByCognomeContaining(String cognome);

}
