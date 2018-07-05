package br.com.hoptech.vaaguruapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.domain.Schedule;
import br.com.hoptech.vaaguruapi.repositories.ScheduleRepository;
import br.com.hoptech.vaaguruapi.services.exceptions.ObjectNotFoundException;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository repo;
    
    public Schedule find(Integer id) {
	Optional<Schedule> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Schedule.class.getName())); 
    }
    
    public List<Schedule> findAll() {
	return repo.findAll();
    }
    
}
