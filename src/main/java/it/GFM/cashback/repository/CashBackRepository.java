package it.GFM.cashback.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.GFM.cashback.model.CashBack;

public interface CashBackRepository extends JpaRepository<CashBack, Long> {
	
	public List<CashBack> findByPrezzo(BigDecimal prezzo);
	public List<CashBack> findByPercentuale(float prezzo);

}
