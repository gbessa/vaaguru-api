package br.com.hoptech.vaaguruapi.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Inscription;
import br.com.hoptech.vaaguruapi.repositories.InscriptionRepository;
import br.com.hoptech.vaaguruapi.services.exceptions.ObjectNotFoundException;

@Service
public class InscriptionService {

    @Autowired
    InscriptionRepository repo;
    
    @Autowired
    RowerService rowerService;
    
    public Inscription find(Integer id) {
	Optional<Inscription> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Inscription.class.getName())); 
    }
    
    @Transactional
    public Inscription insert(Inscription obj) {
	obj.setId(null);
	obj.setTime(new Date());
	obj = repo.save(obj);
	return obj;
    }
    
}
