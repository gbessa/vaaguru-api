package br.com.hoptech.vaaguruapi.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.dto.RowerDTO;
import br.com.hoptech.vaaguruapi.services.RowerService;

@RestController
@RequestMapping("/rowers")
public class RowerResource {

    @Autowired
    RowerService service;
    
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
    
}
