package br.com.hoptech.vaaguruapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.repositories.RowerRepository;
import br.com.hoptech.vaaguruapi.security.UserSS;
import br.com.hoptech.vaaguruapi.services.exceptions.AuthorizationException;
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
    
    public Rower findByEmail(String email) {
	UserSS user = UserService.authenticated();
	//if (user==null || !user.hasRole(Roles.ADMIN) && !email.equals(user.getUsername())) {
	if (user==null || !email.equals(user.getUsername())) {
	    throw new AuthorizationException("Access denied");
	}
	
	Rower obj = repo.findByEmail(email);
	if (obj == null) {
	    throw new ObjectNotFoundException(
		    	"Objeto não encontrado. Email: " + email + ", Tipo: " + Rower.class.getName());
	}
	return obj;
    }    
    
    public List<Rower> findByTeam(Integer teamId) {
	return repo.findByTeams_id(teamId);
    }
    
}
