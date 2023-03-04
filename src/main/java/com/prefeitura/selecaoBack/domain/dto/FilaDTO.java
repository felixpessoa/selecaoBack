package com.prefeitura.selecaoBack.domain.dto;

import java.io.Serializable;

import com.prefeitura.selecaoBack.domain.model.Pessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

    private int numero;
    private Pessoa pessoa;
}
