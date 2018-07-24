package br.com.hoptech.vaaguruapi.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.domain.Team;
import br.com.hoptech.vaaguruapi.repositories.TeamRepository;
import br.com.hoptech.vaaguruapi.security.UserSS;
import br.com.hoptech.vaaguruapi.services.exceptions.ObjectNotFoundException;

@Service
public class TeamService {

    @Autowired
    TeamRepository repo;
    
    @Autowired
    RowerService rowerService;
    
    public Team find(Integer id) {
	Optional<Team> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Team.class.getName())); 
    }
    
    public List<Team> findAll() {
	//return repo.findAll();
	UserSS user = UserService.authenticated();
	String email = user.getUsername();
	Rower rower = rowerService.findByEmail(email);
	return repo.findAll(rower.getId());
    }
    
    @Transactional
    public Team insert(Team obj) {
	obj.setId(null);
	obj = repo.save(obj);
	return obj;
    }
}
