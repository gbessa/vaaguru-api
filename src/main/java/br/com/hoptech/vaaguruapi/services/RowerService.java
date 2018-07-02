package br.com.hoptech.vaaguruapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.repositories.RowerRepository;

@Service
public class RowerService {

    @Autowired
    RowerRepository repo;
    
    public List<Rower> findByTeam(Integer teamId) {
	return repo.findByTeams_id(teamId);
    }
    
}
