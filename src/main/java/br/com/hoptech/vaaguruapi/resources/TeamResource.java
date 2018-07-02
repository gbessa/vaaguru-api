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
import br.com.hoptech.vaaguruapi.domain.Team;
import br.com.hoptech.vaaguruapi.dto.RowerDTO;
import br.com.hoptech.vaaguruapi.dto.TeamDTO;
import br.com.hoptech.vaaguruapi.services.RowerService;
import br.com.hoptech.vaaguruapi.services.TeamService;

@RestController
@RequestMapping("/teams")
public class TeamResource {

    @Autowired
    TeamService service;
    
    @Autowired
    RowerService rowerService;
    
    @GetMapping()
    public ResponseEntity<List<TeamDTO>> findAll() {
	List<Team> list = service.findAll();
	List<TeamDTO> listDto = list.stream().map(obj -> new TeamDTO(obj)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
    }
    
    @GetMapping(value="/{teamId}/rowers")
    public ResponseEntity<List<RowerDTO>> findRowers(@PathVariable Integer teamId) {
	List<Rower> list = rowerService.findByTeam(teamId);
	List<RowerDTO> listDto = list.stream().map(obj -> new RowerDTO(obj))
		.collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
    }
    
}
