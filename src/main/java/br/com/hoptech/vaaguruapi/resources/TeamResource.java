package br.com.hoptech.vaaguruapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.hoptech.vaaguruapi.domain.Invitation;
import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.domain.Team;
import br.com.hoptech.vaaguruapi.dto.InvitationDTO;
import br.com.hoptech.vaaguruapi.dto.RowerDTO;
import br.com.hoptech.vaaguruapi.dto.TeamDTO;
import br.com.hoptech.vaaguruapi.services.InvitationService;
import br.com.hoptech.vaaguruapi.services.RowerService;
import br.com.hoptech.vaaguruapi.services.TeamService;

@RestController
@RequestMapping("/teams")
public class TeamResource {

    @Autowired
    TeamService service;
    
    @Autowired
    RowerService rowerService;
    
    @Autowired
    InvitationService invitationService;
    
    @GetMapping()
    public ResponseEntity<List<TeamDTO>> findAll(@RequestParam(name = "isowner", defaultValue = "false") Boolean isOwner) {
	List<Team> list;
	if (isOwner) list = service.findOwned();
	else list = service.findAll();
	List<TeamDTO> listDto = list.stream().map(obj -> new TeamDTO(obj)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<TeamDTO> find(@PathVariable Integer id) {
	Team obj = service.find(id);
	TeamDTO objDto = new TeamDTO(obj);
	return ResponseEntity.ok().body(objDto);
    }
    
    @GetMapping(value="/{teamId}/rowers")
    public ResponseEntity<List<RowerDTO>> findRowers(@PathVariable Integer teamId) {
	List<Rower> list = rowerService.findByTeam(teamId);
	List<RowerDTO> listDto = list.stream().map(obj -> new RowerDTO(obj))
		.collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value="/{teamId}/invitations")
    public ResponseEntity<List<InvitationDTO>> findInvitations(@PathVariable Integer teamId) {
	List<Invitation> list = invitationService.findByTeam(teamId);
	List<InvitationDTO> listDto = list.stream().map(obj -> new InvitationDTO(obj))
		.collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
    }    
    
    @GetMapping(value="/{teamId}/owners")
    public ResponseEntity<List<RowerDTO>> findOwners(@PathVariable Integer teamId) {
	Team obj = service.find(teamId);
	List<RowerDTO> listDto = obj.getOwners().stream().map(rower -> new RowerDTO(rower))
		.collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody TeamDTO objDto) {
	Team obj = service.fromDTO(objDto);
	obj = service.insert(obj);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	return ResponseEntity.created(uri).build();
    }    
    
    @PostMapping(value="/picture")
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
	URI uri = service.uploadTeamPicture(file);	
	return ResponseEntity.created(uri).build();
    }
    
}
