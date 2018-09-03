package br.com.hoptech.vaaguruapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.hoptech.vaaguruapi.services.EmailService;
import br.com.hoptech.vaaguruapi.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestConfig.class.getName());

//    @Autowired
//    private DBService dbService;
//    
//    @Bean
//    public boolean instantiateDatabase() throws ParseException {
//	dbService.instantiateDatabase();
//	return true;
//    }
    
    @Bean
    public Boolean printLoad() {
	StringBuilder sb = new StringBuilder();
	sb.append("\n##########################################");
	sb.append("\n############## TEST MODE #################");
	sb.append("\n##########################################");
	
	LOGGER.info(sb.toString());
	return true;
    }
    
    @Bean
    public EmailService emailService() {
//	return new SmtpEmailService();
	return new MockEmailService();	
    }
    
}
