package it.GFM.caschback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.GFM.caschback.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
