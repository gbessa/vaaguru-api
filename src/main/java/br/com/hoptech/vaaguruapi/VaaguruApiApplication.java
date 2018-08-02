package br.com.hoptech.vaaguruapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.hoptech.vaaguruapi.services.DBService;

@SpringBootApplication
public class VaaguruApiApplication implements CommandLineRunner {

    	@Autowired
    	DBService dbService;
    
	public static void main(String[] args) {
		SpringApplication.run(VaaguruApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	    dbService.instantiateDatabase();
	}
}
