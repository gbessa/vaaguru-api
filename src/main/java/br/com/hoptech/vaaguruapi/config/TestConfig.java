package br.com.hoptech.vaaguruapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.hoptech.vaaguruapi.services.EmailService;
import br.com.hoptech.vaaguruapi.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

//    @Autowired
//    private DBService dbService;
//    
//    @Bean
//    public boolean instantiateDatabase() throws ParseException {
//	dbService.instantiateDatabase();
//	return true;
//    }
    
    @Bean
    public EmailService emailService() {
//	return new SmtpEmailService();
	return new MockEmailService();	
    }
    
}
