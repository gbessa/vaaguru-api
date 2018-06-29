package br.com.hoptech.vaaguruapi.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hoptech.vaaguruapi.domain.Team;
import br.com.hoptech.vaaguruapi.dto.TeamDTO;
import br.com.hoptech.vaaguruapi.services.TeamService;

@RestController
@RequestMapping("/teams")
public class TeamResource {

    @Autowired
    TeamService service;
    
    @GetMapping()
    public ResponseEntity<List<TeamDTO>> findAll() {
	List<Team> list = service.findAll();
	List<TeamDTO> listDto = list.stream().map(obj -> new TeamDTO(obj)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
    }
    
}
