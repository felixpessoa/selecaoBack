package com.prefeitura.selecaoBack.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prefeitura.selecaoBack.domain.model.Fila;
import com.prefeitura.selecaoBack.domain.model.Pessoa;
import com.prefeitura.selecaoBack.domain.repository.PessoaRepository;
import com.prefeitura.selecaoBack.domain.service.exceptions.ObjectNotFoundException;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;
    @Autowired
    private FilaService filaService;

    //Cria um novo cadastro
    public Pessoa create(Pessoa obj) {
    	Pessoa pessoa = repository.save(obj);
//    	Fila fila = new Fila();
//    	fila.setPessoas(pessoa);
//    	fila.setData(LocalDate.now());
//    	filaService.create(fila);
        return repository.save(obj);
    }

    public List<Pessoa> getAll() {
        return repository.findAll();
    }

    public Pessoa getById(Long id) {
        Optional<Pessoa> opt = repository.findById(id);
        return opt.orElseThrow(() -> new ObjectNotFoundException(
                "Pessoa não encontrado! id: " + id + ", Tipo: " + Pessoa.class.getName()));
    }

    public Pessoa update(Long id, Pessoa obj) {
        if (findByNumero(obj.getNumeroTelefone()) != null && findByNumero(obj.getNumeroTelefone()).getId() != id) {
            throw new ObjectNotFoundException("Número já cadastrado!");
        }
        Pessoa newObj = getById(id);
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    private void updateData(Pessoa newObj, Pessoa obj) {
        newObj.setNome(obj.getNome());
        newObj.setIdade(obj.getIdade());
        newObj.setNumeroTelefone(obj.getNumeroTelefone());
        newObj.setPreferencial(obj.isPreferencial());
    }

    public void deletar(Long id) {
        repository.delete(getById(id));
    }

    
    //Valida se numero tem cadastro.
    public Pessoa getByNumero(String numero) {
        Pessoa pessoaCadastrada = findByNumero(numero);
        if (pessoaCadastrada == null) {
            return pessoaCadastrada;
        }
        throw new ObjectNotFoundException("Vamos Cadastrar seu numero.");

    }

    private Pessoa findByNumero(String numero) {
        Pessoa obj = repository.findByNumeroTelefone(numero);
        if (obj != null) {
            return obj;
        }
        return null;
    }

}
