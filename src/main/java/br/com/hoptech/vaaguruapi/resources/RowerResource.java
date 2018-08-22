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

import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.dto.RowerDTO;
import br.com.hoptech.vaaguruapi.dto.RowerNewDTO;
import br.com.hoptech.vaaguruapi.services.InvitationService;
import br.com.hoptech.vaaguruapi.services.RowerService;

@RestController
@RequestMapping("/rowers")
public class RowerResource {

    @Autowired
    RowerService service;
    
    @Autowired
    InvitationService invitationService;
    
    @GetMapping()
    public ResponseEntity<List<RowerDTO>> findAll() {
	List<Rower> list = service.findAll();
	List<RowerDTO> listDto = list.stream().map(obj -> new RowerDTO(obj)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<RowerDTO> find(@PathVariable Integer id) {
	Rower obj = service.find(id);
	RowerDTO objDto = new RowerDTO(obj);
	return ResponseEntity.ok().body(objDto);
    }

    @GetMapping(value="/me")
    public ResponseEntity<RowerDTO> find() {
	Rower obj = service.findMe();
	RowerDTO objDto = new RowerDTO(obj);
	return ResponseEntity.ok().body(objDto);
    }
    
//    @GetMapping(value="/{rowerId}/invitations")
//    public ResponseEntity<List<InvitationDTO>> findInvitations(@PathVariable Integer rowerId) {
//	List<Invitation> list = invitationService.findByRower(rowerId);
//	List<InvitationDTO> listDto = list.stream().map(obj -> new InvitationDTO(obj))
//		.collect(Collectors.toList());
//	return ResponseEntity.ok().body(listDto);
//    }	
    
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody RowerNewDTO objDto) {
	Rower obj = service.fromDTO(objDto);
	obj = service.insert(obj);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	return ResponseEntity.created(uri).build();
    }
    
    @PostMapping(value="/picture")
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
	URI uri = service.uploadProfilePicture(file);	
	return ResponseEntity.created(uri).build();
    }
    
//    @GetMapping(value="/{email}")
//    public ResponseEntity<RowerDTO> find(@PathVariable String email) {
//	Rower obj = service.findByEmail(email);
//	RowerDTO objDto = new RowerDTO(obj);
//	return ResponseEntity.ok().body(objDto);
//    }
}
