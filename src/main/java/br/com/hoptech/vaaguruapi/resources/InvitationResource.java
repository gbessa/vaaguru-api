package br.com.hoptech.vaaguruapi.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.hoptech.vaaguruapi.domain.Invitation;
import br.com.hoptech.vaaguruapi.dto.InvitationDTO;
import br.com.hoptech.vaaguruapi.services.InvitationService;

@RestController
@RequestMapping("/invitations")
public class InvitationResource {
    
    @Autowired
    InvitationService service;
    
    @GetMapping(value="/{id}")
    public ResponseEntity<InvitationDTO> find(@PathVariable Integer id) {
	Invitation obj = service.find(id);
	InvitationDTO objDto = new InvitationDTO(obj);
	return ResponseEntity.ok().body(objDto);
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody InvitationDTO objDto) {
	Invitation obj = service.fromDTO(objDto);
	obj = service.insert(obj);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	return ResponseEntity.created(uri).build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
	service.delete(id);
	return ResponseEntity.accepted().build();
    }
}
