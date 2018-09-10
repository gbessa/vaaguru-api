package br.com.hoptech.vaaguruapi.config;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DevConfig.class.getName());

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
	StringBuilder sb = new StringBuilder();
	sb.append("\n##########################################");
	sb.append("\n############### DEV MODE #################");
	sb.append("\n##########################################");
	
	LOGGER.info(sb.toString());
	return true;
    }    
    
}
