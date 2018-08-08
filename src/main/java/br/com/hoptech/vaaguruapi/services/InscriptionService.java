package br.com.hoptech.vaaguruapi.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Inscription;
import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.domain.Schedule;
import br.com.hoptech.vaaguruapi.dto.InscriptionNewDTO;
import br.com.hoptech.vaaguruapi.repositories.InscriptionRepository;
import br.com.hoptech.vaaguruapi.services.exceptions.ObjectNotFoundException;

@Service
public class InscriptionService {

    @Autowired
    InscriptionRepository repo;
    
    @Autowired
    RowerService rowerService;
    
    @Autowired
    ScheduleService scheduleService;
    
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
    
    public List<Inscription> findBySchedule(Integer scheduleId) {
	return repo.findBySchedule_id(scheduleId);
    }
    
    public void delete(Integer id) {
	Inscription obj = find(id);
	repo.delete(obj);
    }
    
    public Inscription fromDTO(InscriptionNewDTO objDto) {
	Rower rower;
	Inscription obj = new Inscription();
	if (objDto.getRower_id() != null) {
	    rower = rowerService.find(objDto.getRower_id());	    
	} else {
	    rower = rowerService.findByEmail(objDto.getRower_email());
	}
	Schedule schedule = scheduleService.find(objDto.getSchedule_id());
	obj.setRower(rower);
	obj.setSchedule(schedule);
	return obj;
    }
    
}
