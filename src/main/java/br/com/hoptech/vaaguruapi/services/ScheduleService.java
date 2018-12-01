package br.com.hoptech.vaaguruapi.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.domain.Schedule;
import br.com.hoptech.vaaguruapi.domain.Team;
import br.com.hoptech.vaaguruapi.dto.ScheduleNewDTO;
import br.com.hoptech.vaaguruapi.repositories.ScheduleRepository;
import br.com.hoptech.vaaguruapi.services.exceptions.DataIntegrityException;
import br.com.hoptech.vaaguruapi.services.exceptions.ObjectNotFoundException;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository repo;
    
    @Autowired
    RowerService rowerService;
    
    @Autowired
    TeamService teamService;
    
    public Schedule find(Integer id) {
	Optional<Schedule> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Schedule.class.getName())); 
    }
    
    public List<Schedule> findAll() {
	//return repo.findAll();
	return repo.findAllByTeamIn(teamService.findAll());
    }
    
    public List<Schedule> findAllTomorrow() {
	Date startDay = new Date();
	startDay.setHours(0);
	Date endDay = new Date();
	endDay.setHours(23);
	return repo.findByDateBetween(startDay, endDay);
    }
    
    public Schedule fromDTO(ScheduleNewDTO objDto) {
	Rower rower = rowerService.findByEmail(objDto.getRowerResponsable_email());
	Team team = teamService.find(objDto.getTeam_id());
	return new Schedule(null, objDto.getDate(), 1, objDto.getNumOfSeats(), team, rower, objDto.getObs());
    }
    
    @Transactional
    public Schedule insert(Schedule obj) {
	obj.setId(null);
	obj = repo.save(obj);
	return obj;
    }
    
    public void delete(Integer id) {
	Schedule obj = find(id);
	try {
	    repo.delete(obj);	    
	}catch (DataIntegrityViolationException e) {
	    throw new DataIntegrityException("Não é possível excluir uma Agenda que possui Inscrições");
	}
    }
}
