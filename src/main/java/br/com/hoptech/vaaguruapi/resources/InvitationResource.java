package br.com.hoptech.vaaguruapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.hoptech.vaaguruapi.domain.Invitation;
import br.com.hoptech.vaaguruapi.dto.InvitationDTO;
import br.com.hoptech.vaaguruapi.dto.InvitationNewDTO;
import br.com.hoptech.vaaguruapi.security.UserSS;
import br.com.hoptech.vaaguruapi.services.InvitationService;
import br.com.hoptech.vaaguruapi.services.UserService;

@RestController
@RequestMapping("/invitations")
public class InvitationResource {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(InvitationResource.class.getName());
    
    @Autowired
    InvitationService service;
    
    @GetMapping()
    public ResponseEntity<List<InvitationDTO>> findInvitations() {
	UserSS user = UserService.authenticated();
	String email = user.getUsername();
	List<Invitation> list = service.findByInvitedEmail(email);
	List<InvitationDTO> listDto = list.stream().map(obj -> new InvitationDTO(obj))
		.collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
    } 
    
    @GetMapping(value="/{id}")
    public ResponseEntity<InvitationDTO> find(@PathVariable Integer id) {
	Invitation obj = service.find(id);
	InvitationDTO objDto = new InvitationDTO(obj);
	return ResponseEntity.ok().body(objDto);
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody InvitationNewDTO objDto) {
	Invitation obj = service.fromDTO(objDto);
	obj = service.insert(obj);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	return ResponseEntity.created(uri).build();
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody InvitationDTO objDto, @PathVariable Integer id) {
	LOGGER.info("Entrou");
	Invitation obj = service.fromDTO(objDto);
	obj.setId(id);
	obj = service.update(obj);
	return ResponseEntity.noContent().build();
    }    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
	service.delete(id);
	return ResponseEntity.accepted().build();
    }
}
