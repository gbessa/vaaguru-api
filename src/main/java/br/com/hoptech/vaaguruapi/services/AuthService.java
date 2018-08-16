package br.com.hoptech.vaaguruapi.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.repositories.RowerRepository;
import br.com.hoptech.vaaguruapi.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

    @Autowired
    private RowerRepository rowerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    private Random rand = new Random();

    public void sendNewPassword(String email) {

	Rower rower = rowerRepository.findByEmail(email);
	if (rower == null) {
	    throw new ObjectNotFoundException("Email not found: " + email);
	}

	String newPass = newPassword();
	rower.setPassword(bCryptPasswordEncoder.encode(newPass));

	rowerRepository.save(rower);

	emailService.sendNewPassawordEmail(rower, newPass);

    }

    private String newPassword() {
	char[] vet = new char[10];
	for (int i = 0; i < 10; i++) {
	    vet[i] = randomChar();
	}
	return new String(vet);
    }

    private char randomChar() {
	int opt = rand.nextInt(3);
	switch (opt) {
	case 0: // gen number
	    return (char) (rand.nextInt(10) + 48);
	    
	case 1: // gen ucase
	    return (char) (rand.nextInt(26) + 25);
	    
	case 2: // gen lcase
	    return (char) (rand.nextInt(26) + 97);
	    
	default:
	    return (char) 0;	    
	}
    }

}
