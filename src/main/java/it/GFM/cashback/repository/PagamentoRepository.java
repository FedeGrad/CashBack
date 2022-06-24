package it.GFM.cashback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.GFM.cashback.model.EPagamento;
import it.GFM.cashback.model.Pagamento;
import it.GFM.cashback.model.Utente;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
	
	@Query(value = "SELECT * FROM Pagamento "
			+ "JOIN Utente ON Pagamento.id_utente = Utente.id"
			+ "WHERE Utente.username = ?1", nativeQuery = true)
	public List<Pagamento> findByUtente(String username);
	public List<Pagamento> findByIban(String iban);
	public boolean existsByIban(String iban);
	public List<Pagamento> findByNumeroCarta(Long numeroCarta);
	public boolean existsByNumeroCarta(Long numeroCarta);
	public List<Pagamento> findByPaypalUser(String paypalUser);
	public boolean existsByPaypalUser(String paypalUser);
	public List<Pagamento> findByTipo(EPagamento tipo);
	
}
