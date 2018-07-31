package br.com.hoptech.vaaguruapi.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Inscription;
import br.com.hoptech.vaaguruapi.domain.Invitation;
import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.domain.Schedule;
import br.com.hoptech.vaaguruapi.dto.InvitationDTO;
import br.com.hoptech.vaaguruapi.repositories.InvitationRepository;
import br.com.hoptech.vaaguruapi.services.exceptions.ObjectNotFoundException;

@Service
public class InvitationService {
    
    @Autowired
    InvitationRepository repo;
    
    public Invitation find(Integer id) {
	Optional<Invitation> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Invitation.class.getName())); 
    }
    
    @Transactional
    public Invitation insert(Invitation obj) {
	obj.setId(null);
	obj.setTime(new Date());
	obj = repo.save(obj);
	return obj;
    }
    
    public List<Invitation> findByTeam(Integer teamId) {
	return repo.findByTeam_id(teamId);
    }
    
    public void delete(Integer id) {
	Invitation obj = find(id);
	repo.delete(obj);
    }

    public Invitation fromDTO(InvitationDTO objDto) {
	Rower rower;
	Inscription obj = new Inscription();
	if (objDto.getRower_id() != null) {
	    System.out.println("123");
	    rower = rowerService.find(objDto.getRower_id());	    
	} else {
	    System.out.println("321 ==> " + objDto.getRower_email());
	    rower = rowerService.findByEmail(objDto.getRower_email());
	}
	Schedule schedule = scheduleService.find(objDto.getSchedule_id());
	obj.setRower(rower);
	obj.setSchedule(schedule);
	return obj;
    }
}
