package br.com.hoptech.vaaguruapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.repositories.RowerRepository;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private RowerRepository rowerRepository;

    @Override
    public String execute(Connection<?> connection) {
    	Rower rower = new Rower();
	rower.setName(connection.getDisplayName());
	//rower.setEmail(connection.);
	rower.setPassword("123");
	rowerRepository.save(rower);
	return rower.getName();
	
    }
}
