package br.com.hoptech.vaaguruapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.repositories.RowerRepository;
import br.com.hoptech.vaaguruapi.services.exceptions.ObjectNotFoundException;

@Service
public class RowerService {

    @Autowired
    RowerRepository repo;
    
    public Rower find(Integer id) {
	Optional<Rower> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Rower.class.getName())); 
    }
    
    public List<Rower> findAll() {
	return repo.findAll();
    }
    
    public List<Rower> findByTeam(Integer teamId) {
	return repo.findByTeams_id(teamId);
    }
    
}
