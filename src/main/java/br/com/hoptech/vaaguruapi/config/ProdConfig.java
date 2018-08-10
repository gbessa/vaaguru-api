package br.com.hoptech.vaaguruapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.hoptech.vaaguruapi.services.EmailService;
import br.com.hoptech.vaaguruapi.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {
    
    @Bean
    public EmailService emailService() {
	return new SmtpEmailService();	
    }
    
}
