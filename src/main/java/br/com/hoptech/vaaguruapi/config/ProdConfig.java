package br.com.hoptech.vaaguruapi.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.hoptech.vaaguruapi.services.DBService;
import br.com.hoptech.vaaguruapi.services.EmailService;
import br.com.hoptech.vaaguruapi.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {
        
    @Autowired
    private DBService dbService;
    
    @Value("${vaaguru.database.instantiate}")
    private String instantiateDatabaseFlag;
    
    @Bean
    public boolean instantiateDatabase() throws ParseException {
	System.out.println(instantiateDatabaseFlag);
	if (instantiateDatabaseFlag == "true") {
	    System.out.println("Entrou");
	    dbService.instantiateDatabase();
	    return true;
	}
	return false;
    }
    
    @Bean
    public EmailService emailService() {
	return new SmtpEmailService();	
    }
    
}
