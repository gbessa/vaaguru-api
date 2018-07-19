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

import br.com.hoptech.vaaguruapi.domain.Inscription;
import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.domain.Schedule;
import br.com.hoptech.vaaguruapi.dto.InscriptionDTO;
import br.com.hoptech.vaaguruapi.dto.InscriptionNewDTO;
import br.com.hoptech.vaaguruapi.services.InscriptionService;
import br.com.hoptech.vaaguruapi.services.RowerService;
import br.com.hoptech.vaaguruapi.services.ScheduleService;

@RestController
@RequestMapping("/inscriptions")
public class InscriptionResource {

    @Autowired
    InscriptionService service;
    
    @Autowired
    RowerService rowerService;
    
    @Autowired
    ScheduleService scheduleService;
    
    @GetMapping(value="/{id}")
    public ResponseEntity<InscriptionDTO> find(@PathVariable Integer id) {
	Inscription obj = service.find(id);
	InscriptionDTO objDto = new InscriptionDTO(obj);
	return ResponseEntity.ok().body(objDto);
    }
        
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody InscriptionNewDTO objDto) {
	Rower rower;
	Inscription obj = new Inscription(objDto);
	if (objDto.getRower_id() != null) {
	    System.out.println("123");
	    rower = rowerService.find(objDto.getRower_id());	    
	} else {
	    System.out.println("321 ==> " + objDto.getRower_email());
	    rower = rowerService.findByEmail(objDto.getRower_email());
	}
	System.out.println("88888888888");
	System.out.println(rower);
	Schedule schedule = scheduleService.find(objDto.getSchedule_id());
	obj.setRower(rower);
	obj.setSchedule(schedule);
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
