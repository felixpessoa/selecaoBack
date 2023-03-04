package com.prefeitura.selecaoBack.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prefeitura.selecaoBack.domain.model.Fila;
import com.prefeitura.selecaoBack.domain.repository.FilaRepository;
import com.prefeitura.selecaoBack.domain.service.exceptions.ObjectNotFoundException;

@Service
public class FilaService {

    @Autowired
    private FilaRepository repository;

    public Fila create(Fila obj) {
    	if(obj.getPessoas().isPreferencial()) {
    		
    	}
    	Fila fila = jaCadastrado(obj);
    	if(fila!=null) {
    		 fila = repository.save(obj);
    		 fila.setNumero(posicao(obj));
    		 return fila;
    	}
    	update(fila.getId(), fila);
        return fila;
    }

    public List<Fila> getAll() {
        return repository.findAll();
    }

    public Fila getById(Long id) {
        Optional<Fila> opt = repository.findById(id);
        return opt.orElseThrow(() -> new ObjectNotFoundException(
                "Fila não encontrado! id: " + id + ", Tipo: " + Fila.class.getName()));
    }
    

    public Fila update(Long id, Fila obj) {
        Fila newObj = getById(id);
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    private void updateData(Fila newObj, Fila obj) {
        newObj.setNumero(obj.getNumero());
        // newObj.setIdade(obj.getIdade());
       
    }

    public void deletar(Long id) {
        repository.delete(getById(id));
    }

    private int posicao(Fila obj) {
    	Fila fila = repository.findByDataMaxNumero(obj.getData());
    	return fila.getNumero()+1;	
    }
    
    private Fila jaCadastrado(Fila obj) {
//    	Fila fila = repository.findByPessoa(obj.getPessoas(), obj.getData());
    	Fila fila;
//    	if (fila != null) {
//    		return fila;
//    	}
    	return null;
    }

}
