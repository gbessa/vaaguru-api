package br.com.hoptech.vaaguruapi.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
public class CountryResource {

    @GetMapping()
    public ResponseEntity<List<String>> findAll() {
	List<String> listCountries = new ArrayList<String>();
	
	String[] locales = Locale.getISOCountries();
	for (String countryCode : locales) {
	    Locale obj = new Locale("", countryCode);
	    listCountries.add(countryCode + "-" + obj.getDisplayCountry());
	}
	
	return ResponseEntity.ok().body(listCountries);
    }
    
}
