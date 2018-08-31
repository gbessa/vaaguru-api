package br.com.hoptech.vaaguruapi.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Invitation;
import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.domain.Team;
import br.com.hoptech.vaaguruapi.dto.InvitationDTO;
import br.com.hoptech.vaaguruapi.repositories.InvitationRepository;
import br.com.hoptech.vaaguruapi.services.exceptions.ObjectNotFoundException;

@Service
public class InvitationService {
    
    @Autowired
    InvitationRepository repo;
    
    @Autowired
    TeamService teamService;
    
    @Autowired
    RowerService rowerService;
    
    @Autowired
    private EmailService emailService;
    
    public Invitation find(Integer id) {
	Optional<Invitation> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Invitation.class.getName())); 
    }
    
    public List<Invitation> findByTeam(Integer teamId) {
	return repo.findByTeam_id(teamId);
    }
    
    public List<Invitation> findByInvitedEmail(String email) {
	return repo.findByInvitedEmail(email);
    }
    
    @Transactional
    public Invitation insert(Invitation obj) {
	obj.setId(null);
	obj.setTime(new Date());
	obj = repo.save(obj);
	//emailService.sendInvitationEmail(obj);
	emailService.sendInvitationHtmlEmail(obj);
	return obj;
    }
    
    public void delete(Integer id) {
	Invitation obj = find(id);
	repo.delete(obj);
    }

    public Invitation fromDTO(InvitationDTO objDto) {
	Team team = teamService.find(objDto.getTeam_id());
	Rower inviter = rowerService.findByEmail(objDto.getInviter_email());
	Invitation obj = new Invitation(null, null, team, inviter, objDto.getInvited_email(), objDto.getStatus());
	return obj;
    }
}
