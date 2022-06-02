package it.GFM.caschback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.GFM.caschback.model.EPagamento;
import it.GFM.caschback.model.Pagamento;
import it.GFM.caschback.model.Utente;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
	
	@Query(value = "", nativeQuery = true)
	public List<Pagamento> findByUtente(String username);
	public List<Pagamento> findByIban(String iban);
	public boolean existsByIban(String iban);
	public List<Pagamento> findByNumeroCarta(Long numeroCarta);
	public boolean existsByNumeroCarta(Long numeroCarta);
	public List<Pagamento> findByPaypalUser(String paypalUser);
	public boolean existsByPaypalUser(String paypalUser);
	public List<Pagamento> findByTipo(EPagamento tipo);
	
}
