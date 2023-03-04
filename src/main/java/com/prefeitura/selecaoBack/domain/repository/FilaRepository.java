package com.prefeitura.selecaoBack.domain.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prefeitura.selecaoBack.domain.model.Fila;
import com.prefeitura.selecaoBack.domain.model.Pessoa;

@Repository
public interface FilaRepository extends JpaRepository<Fila, Long>{
	
	@Query("SELECT MAX(f.numero) FROM Fila f where f.data = :data")
	Fila findByDataMaxNumero(@Param("data")LocalDate data);
    

//	@Query("SELECT f FROM Fila f where f.data = :data and Pessoa = :pessoa and ")
//	Fila findByPessoaPrefe(@Param("pessoa") Pessoa pessoa, @Param("data") LocalDate data);
	
}
