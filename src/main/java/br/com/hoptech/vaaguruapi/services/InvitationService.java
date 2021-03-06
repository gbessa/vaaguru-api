package br.com.hoptech.vaaguruapi.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Invitation;
import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.domain.Team;
import br.com.hoptech.vaaguruapi.dto.InvitationDTO;
import br.com.hoptech.vaaguruapi.dto.InvitationNewDTO;
import br.com.hoptech.vaaguruapi.repositories.InvitationRepository;
import br.com.hoptech.vaaguruapi.services.exceptions.DataIntegrityException;
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
	return repo.findByTeam_idAndStatus(teamId, 1);
    }
    
    public List<Invitation> findByInvitedEmail(String email) {
	return repo.findByInvitedEmailAndStatus(email, 1);
    }
    
    @Transactional
    public Invitation insert(Invitation obj) {
	
	//Verify 1
	final String invitedEmail = obj.getInvitedEmail();
	if (!(obj.getTeam().getMembersAndOwners().stream()
		.filter(rower -> rower.getEmail().equals(invitedEmail))
		.collect(Collectors.toList()).isEmpty())) {
	    throw new DataIntegrityException("This email already is a Team Member");
	}
	
	//Verify 2
	List<Invitation> openInvites = findByInvitedEmail(obj.getInvitedEmail());
	if (!openInvites.isEmpty()) {
	    throw new DataIntegrityException("This email already has a pendding invitation");
	}
	
	obj.setId(null);
	obj.setTime(new Date());
	obj = repo.save(obj);
	emailService.sendInvitationHtmlEmail(obj);
	return obj;
    }
    
    public Invitation update(Invitation obj) {
	Invitation newObj = find(obj.getId());
	updateData(newObj, obj);
	Rower rowerInvited = rowerService.findByEmail(obj.getInvitedEmail());
	rowerInvited.getTeamsMember().add(obj.getTeam());
	return repo.save(newObj);
    }

    private void updateData(Invitation newObj, Invitation obj) {
	newObj.setStatus(obj.getStatus());
    }    
    
    public void delete(Integer id) {
	Invitation obj = find(id);
	repo.delete(obj);
    }

    public Invitation fromDTO(InvitationNewDTO objDto) {
	Team team = teamService.find(objDto.getTeam_id());
	Rower inviter = rowerService.findByEmail(objDto.getInviter_email());
	Invitation obj = new Invitation(null, null, team, inviter, objDto.getInvited_email(), objDto.getStatus());
	return obj;
    }
    
    public Invitation fromDTO(InvitationDTO objDto) {
	Team team = teamService.find(objDto.getTeam_id());
	Rower inviter = rowerService.findByEmail(objDto.getInviter_email());
	Invitation obj = new Invitation(null, null, team, inviter, objDto.getInvited_email(), objDto.getStatus());
	return obj;
    }
}
