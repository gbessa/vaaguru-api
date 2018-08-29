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

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hoptech.vaaguruapi.dto.CredentialsDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {
    
    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
	setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
	this.authenticationManager = authenticationManager;
	this.jwtUtil = jwtUtil;
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
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		StringBuilder result = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
		    result.append(line);
		}
		rd.close();
		
		System.out.println(result.toString());
		
		email = "gbvirtual@gmail.com";
		password = "123";
	    }
	    
	///*	URL url = new URL(urlString);
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		StringBuilder result = new StringBuilder();
//		conn.setRequestMethod("GET");
//		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	//	
//		String line;
//		while ((line = rd.readLine()) != null) {
//		    result.append(line);
//		}
//		rd.close();*/
	    
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
