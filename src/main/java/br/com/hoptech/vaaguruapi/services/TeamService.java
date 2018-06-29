package br.com.hoptech.vaaguruapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Team;
import br.com.hoptech.vaaguruapi.repositories.TeamRepository;

@Service
public class TeamService {

    @Autowired
    TeamRepository repo;
    
    public List<Team> findAll() {
	return repo.findAll();
    }
    
}
