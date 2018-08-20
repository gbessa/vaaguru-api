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
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;
    
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;
    
    @Bean
    public boolean instantiateDatabase() throws ParseException {
	if (!"create".equals(strategy)) {
	    return false;
	}
	dbService.instantiateDatabase();
	return true;
    }
    
    @Bean
    public EmailService emailService() {
	return new SmtpEmailService();	
    }
    
    @Bean
    public Boolean printLoad() {
	System.out.println("\n##########################################");
	System.out.println("############### DEV MODE ################");
	System.out.println("##########################################\n");
	return true;
    }    
    
}
