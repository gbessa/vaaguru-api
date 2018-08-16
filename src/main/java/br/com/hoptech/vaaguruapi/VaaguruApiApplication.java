package br.com.hoptech.vaaguruapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.hoptech.vaaguruapi.services.DBService;
import br.com.hoptech.vaaguruapi.services.RowerService;

@SpringBootApplication
public class VaaguruApiApplication implements CommandLineRunner {

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
		System.out.println("===== POPULANDO BD ==========");
		dbService.instantiateDatabase();		
	    }
	}
}
