package br.com.hoptech.vaaguruapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.hoptech.vaaguruapi.services.DBService;
import br.com.hoptech.vaaguruapi.services.RowerService;

@EnableScheduling
@SpringBootApplication
public class VaaguruApiApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(VaaguruApiApplication.class.getName());
    
    @Autowired
    private DBService dbService;
    
    @Autowired
    private RowerService rowerService;
    
	public static void main(String[] args) {
		SpringApplication.run(VaaguruApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	    
	    if (rowerService.findAll().isEmpty()) {
		LOGGER.info("===== POPULANDO BD ==========");
		dbService.instantiateDatabase();		
	    }
	}
}
