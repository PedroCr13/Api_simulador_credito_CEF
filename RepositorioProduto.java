package com.simulador.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.simulador.entidade.Produto;

public interface RepositorioProduto extends JpaRepository<Produto, Long>{
	
	@Query(value = "SELECT P.CO_PRODUTO, P.NO_PRODUTO, P.PC_TAXA_JUROS, P.NU_MINIMO_MESES," + 
			       "P.NU_MAXIMO_MESES, P.VR_MINIMO, P.VR_MAXIMO FROM PRODUTO P " + 
				   "WHERE (:VALOR BETWEEN P.VR_MINIMO AND P.VR_MAXIMO " +
			       "AND P.VR_MAXIMO IS NOT NULL) " + 
				   "OR (:VALOR >= P.VR_MINIMO AND P.VR_MAXIMO IS NULL)", nativeQuery = true)
	List<Produto> findByProdutoAdequado(@Param("VALOR") double valor); 

}
