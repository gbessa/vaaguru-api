package br.com.hoptech.vaaguruapi.resources;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hoptech.vaaguruapi.dto.EmailDTO;
import br.com.hoptech.vaaguruapi.security.JWTUtil;
import br.com.hoptech.vaaguruapi.security.UserSS;
import br.com.hoptech.vaaguruapi.services.AuthService;
import br.com.hoptech.vaaguruapi.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;
    
    @Autowired
    private AuthService service;
    
    private AuthenticationManager authManager;

    @PostMapping(value="/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
	UserSS user = UserService.authenticated();
	String token = jwtUtil.generateToken(user.getUsername());
	response.addHeader("Authorization", "Bearer " + token);
	response.addHeader("access-control-expose-headers", "Authorization");
	return ResponseEntity.noContent().build();
    }
    
    @PostMapping(value="/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
	service.sendNewPassword(objDto.getEmail());
	return ResponseEntity.noContent().build();
    }
    
    @PostMapping(value="/facebook")
    public ResponseEntity<Void> facebookLogin(@Valid @RequestBody String token, HttpServletResponse response) throws IOException {
	System.out.println("Facebook Login => " + token);
	    
//	String urlString = "https://graph.facebook.com/";
	//String urlString = "https://vaaguru-api.herokuapp.com/countries";

///*	URL url = new URL(urlString);
//	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//	StringBuilder result = new StringBuilder();
//	conn.setRequestMethod("GET");
//	BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//	
//	String line;
//	while ((line = rd.readLine()) != null) {
//	    result.append(line);
//	}
//	rd.close();*/
	
//	System.out.println(result.toString());
	
	
//	UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken("gbvirtual@gmail.com", "123");
//	System.out.println(authReq.getName() + " --> " + authReq.getCredentials().toString());
//	System.out.println(authManager.toString());
//	
//	Authentication auth = authManager.authenticate(authReq);
//	
//	SecurityContext securityContext = SecurityContextHolder.getContext();
//	securityContext.setAuthentication(auth);
//
//	UserSS user2 = UserService.authenticated();
//	String token2 = jwtUtil.generateToken(user2.getUsername());
//	response.addHeader("Authorization", "Bearer " + token2);
//	response.addHeader("access-control-expose-headers", "Authorization");
	return ResponseEntity.noContent().build();	
    }
    
}