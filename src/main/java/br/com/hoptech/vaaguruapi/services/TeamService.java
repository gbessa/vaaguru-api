package br.com.hoptech.vaaguruapi.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.hoptech.vaaguruapi.VaaguruApiApplication;
import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.domain.Team;
import br.com.hoptech.vaaguruapi.dto.TeamDTO;
import br.com.hoptech.vaaguruapi.repositories.RowerRepository;
import br.com.hoptech.vaaguruapi.repositories.TeamRepository;
import br.com.hoptech.vaaguruapi.security.UserSS;
import br.com.hoptech.vaaguruapi.services.exceptions.AuthorizationException;
import br.com.hoptech.vaaguruapi.services.exceptions.ObjectNotFoundException;

@Service
public class TeamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamService.class.getName());

    @Autowired
    TeamRepository repo;
    
    @Autowired
    RowerService rowerService;	
    
    @Autowired
    RowerRepository rowerRepository;
    
    @Autowired
    ImageService imageService;
    
    @Autowired
    S3Service s3Service;
    
    @Value("${img.prefix.client.profile}")
    private String imgPrefix;
    
    @Value("${img.profile.size}")
    private int imgSize;
    
    public Team find(Integer id) {
	Optional<Team> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Team.class.getName())); 
    }
    
    public List<Team> findAll() {
	//return repo.findAll();
	UserSS user = UserService.authenticated();
	String email = user.getUsername();
	Rower rower = rowerService.findByEmail(email);
	return repo.findAll(rower.getId());
    }
    
    public List<Team> findOwned() {
 	UserSS user = UserService.authenticated();
 	String email = user.getUsername();
 	Rower rower = rowerService.findByEmail(email);
 	return repo.findOwned(rower.getId());
     }    
    
    public Team fromDTO(TeamDTO objDto) {
	Team obj = new Team(null, objDto.getName(), objDto.getDescription(), objDto.getImageUrl());
	//List<Rower> owners = objDto.getOwners().stream().map(owner -> rowerService.findByEmail(owner.getEmail())).collect(Collectors.toList());
	Rower owner = rowerService.findByEmail(objDto.getOwner_email());
	obj.getOwners().add(owner);
	return obj;
    }    

    @Transactional
    public Team insert(Team obj) {
	obj.setId(null);
	obj = repo.save(obj);
	
	Rower owner = rowerService.findByEmail(obj.getOwners().get(0).getEmail());
	owner.getTeamsOwner().add(obj);
	rowerRepository.save(owner);
	return obj;
    }
    
    public URI uploadTeamPicture(MultipartFile multipartFile) {
	
	UserSS user = UserService.authenticated();
	if (user == null) {
		throw new AuthorizationException("Access denied");
    	}
	
	BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
	jpgImage = imageService.cropSquare(jpgImage);
	jpgImage = imageService.resize(jpgImage, imgSize);
	
	String fileName = imgPrefix + user.getId() + ".jpg";
	
	return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	
    }
    
    public void delete(Integer id) {
	Team obj = find(id);
	repo.delete(obj);
    }
    
    public void unsubscribeMe(Team obj) {
	UserSS user = UserService.authenticated();
 	String email = user.getUsername();
 	Rower rower = rowerService.findByEmail(email);
 	List<Rower> members = obj.getMembers();
 	Integer index = members.indexOf(rower);
 	members.remove(rower);
 	obj.setMembers(members);
 	repo.save(obj);
    }
}
