package br.com.hoptech.vaaguruapi.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.repositories.RowerRepository;
import br.com.hoptech.vaaguruapi.security.UserSS;
import br.com.hoptech.vaaguruapi.services.exceptions.AuthorizationException;
import br.com.hoptech.vaaguruapi.services.exceptions.ObjectNotFoundException;

@Service
public class RowerService {

    @Autowired
    RowerRepository repo;
    
    @Autowired
    ImageService imageService;
    
    @Autowired
    private S3Service s3Service;
    
    @Value("${img.prefix.client.profile}")
    private String imgPrefix;
    
    @Value("${img.profile.size}")
    private int imgSize;
    
    public Rower find(Integer id) {
	Optional<Rower> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Rower.class.getName())); 
    }
    
    public List<Rower> findAll() {
	return repo.findAll();
    }
    
    public Rower findMe() {
	UserSS user = UserService.authenticated();
	//if (user==null || !user.hasRole(Roles.ADMIN) && !email.equals(user.getUsername())) {
	String email = user.getUsername();
	Rower obj = repo.findByEmail(email);
	if (obj == null) {
	    throw new ObjectNotFoundException(
		    	"Objeto não encontrado. Email: " + email + ", Tipo: " + Rower.class.getName());
	}
	return obj;
    } 
    
    public Rower findByEmail(String email) {
	UserSS user = UserService.authenticated();
	//if (user==null || !user.hasRole(Roles.ADMIN) && !email.equals(user.getUsername())) {
	if (user==null || !email.equals(user.getUsername())) {
	    throw new AuthorizationException("Access denied");
	}
	
	Rower obj = repo.findByEmail(email);
	if (obj == null) {
	    throw new ObjectNotFoundException(
		    	"Objeto não encontrado. Email: " + email + ", Tipo: " + Rower.class.getName());
	}
	return obj;
    }    
    
    public List<Rower> findByTeam(Integer teamId) {
	return repo.findByTeamsMember_id(teamId);
    }
    
    public URI uploadProfilePicture(MultipartFile multipartFile) {
	
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
    
}
