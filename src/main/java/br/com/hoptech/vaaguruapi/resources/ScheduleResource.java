package br.com.hoptech.vaaguruapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import br.com.hoptech.vaaguruapi.domain.Schedule;
import br.com.hoptech.vaaguruapi.dto.InscriptionDTO;
import br.com.hoptech.vaaguruapi.dto.ScheduleDTO;
import br.com.hoptech.vaaguruapi.dto.ScheduleNewDTO;
import br.com.hoptech.vaaguruapi.services.InscriptionService;
import br.com.hoptech.vaaguruapi.services.ScheduleService;
import br.com.hoptech.vaaguruapi.services.TeamService;

@RestController
@RequestMapping("/schedules")
public class ScheduleResource {

    @Autowired
    ScheduleService service;

    @Autowired
    InscriptionService inscriptionService;
    
    @Autowired
    TeamService teamService;
    
    @GetMapping()
    public ResponseEntity<List<ScheduleDTO>> findAll() {
	List<Schedule> list = service.findAll();
	List<ScheduleDTO> listDto = list.stream().map(obj -> new ScheduleDTO(obj)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
    }
    
    @GetMapping(value = "/tomorrow")
    public ResponseEntity<List<ScheduleDTO>> findAllTomorrow() {
	List<Schedule> list = service.findAllTomorrow();
	List<ScheduleDTO> listDto = list.stream().map(obj -> new ScheduleDTO(obj)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ScheduleDTO> find(@PathVariable Integer id) {
	Schedule obj = service.find(id);
	ScheduleDTO objDto = new ScheduleDTO(obj);
	return ResponseEntity.ok().body(objDto);
    }

    @GetMapping(value="/{scheduleId}/inscriptions")
    public ResponseEntity<List<InscriptionDTO>> findInscriptions(@PathVariable Integer scheduleId) {
	List<Inscription> list = inscriptionService.findBySchedule(scheduleId);
	List<InscriptionDTO> listDto = list.stream().map(obj -> new InscriptionDTO(obj)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ScheduleNewDTO objDto) {
	Schedule obj = service.fromDTO(objDto);
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
