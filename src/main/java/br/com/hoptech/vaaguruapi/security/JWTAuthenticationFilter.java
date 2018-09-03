package br.com.hoptech.vaaguruapi.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.dto.CredentialsDTO;
import br.com.hoptech.vaaguruapi.repositories.RowerRepository;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class.getName());
    
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;
    private RowerRepository rowerRepository;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, RowerRepository rowerRepository) {
	setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
	this.authenticationManager = authenticationManager;
	this.jwtUtil = jwtUtil;
	this.rowerRepository = rowerRepository;    
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

	try {
	    CredentialsDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);
	    String email;
	    String password;
	    if (creds.getFacebookToken().equals("")) {
		email = creds.getEmail();
		password = creds.getPassword();
	    } else {
		
		String urlString = "https://graph.facebook.com/1953600424695985?fields=email";
		URL url = new URL(urlString);				
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization", "Bearer " + creds.getFacebookToken());
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder result = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
		    result.append(line);
		}
		rd.close();		
		conn.disconnect();
		
		LOGGER.info(result.toString());
		CredentialsDTO cred = new ObjectMapper().readValue(result.toString(), CredentialsDTO.class);
		email = cred.getEmail();
		LOGGER.info(email);
		
		Rower rower = rowerRepository.findByEmail(email);
		password = rower.getPassword();
		LOGGER.info(password);
		//password = "1234";
	    }
	    
	    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());

	    Authentication auth = authenticationManager.authenticate(authToken);
	    
	    return auth;
	    
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse res, FilterChain chain,
	    Authentication auth) throws IOException, ServletException {
	String username = ((UserSS) auth.getPrincipal()).getUsername();
	String token = jwtUtil.generateToken(username);
	System.out.println("token generated: " + token);
	res.addHeader("Authorization", "Bearer " + token);
	res.addHeader("access-control-expose-headers", "Authorization");
    }

    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException exception) throws IOException, ServletException {
	    response.setStatus(401);
	    response.setContentType("application/json");
	    response.getWriter().append(json());
	}

	private String json() {
	    long date = new Date().getTime();
	    return "{\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"Not authorized\", "
		    + "\"message\": \"Email or password invalid\", " + "\"path\": \"/login\"}";
	}

    }
}
