package com.prefeitura.selecaoBack.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.prefeitura.selecaoBack.domain.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

    @Transactional(readOnly = true)
    Pessoa findByNumeroTelefone(String numeroTelefone);
    
}
