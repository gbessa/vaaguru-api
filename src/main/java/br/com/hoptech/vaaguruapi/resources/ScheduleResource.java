package br.com.hoptech.vaaguruapi.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hoptech.vaaguruapi.domain.Schedule;
import br.com.hoptech.vaaguruapi.dto.ScheduleDTO;
import br.com.hoptech.vaaguruapi.services.ScheduleService;

@RestController
@RequestMapping("/schedules")
public class ScheduleResource {

    @Autowired
    ScheduleService service;
    
    @GetMapping()
    public ResponseEntity<List<ScheduleDTO>> findAll() {
	List<Schedule> list = service.findAll();
	List<ScheduleDTO> listDto = list.stream().map(obj -> new ScheduleDTO(obj)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<ScheduleDTO> find(@PathVariable Integer id) {
	Schedule obj = service.find(id);
	ScheduleDTO objDto = new ScheduleDTO(obj);
	return ResponseEntity.ok().body(objDto);
    }
    
}
