package br.com.hoptech.vaaguruapi.services;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Forecast;

@Service
public class WGScrapperService {
    
    private Document doc = null;
    private String actualString = "";
    
    public Forecast scrapSpot(String spotId) {
	getDocument(spotId);
	actualString = findDataInScriptTagNodes();
	System.out.println(actualString);
	return null;
    }
    
    private void getDocument(String spotId) {
	
        try {
            String URL = "https://www.windguru.cz/" + spotId;
            doc = Jsoup.connect(URL).get();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String findDataInScriptTagNodes() {
        String out = "";

        if(doc != null){
            System.out.println("qwe 2");
            Elements scriptElements = doc.getElementsByTag("script");

            // Here we assume than a line is a json object if it contains both opening and closing bracket
            out = scriptElements.stream()
                    .map(Element::data)
                    .filter(s -> s.contains("wg_fcst_tab_data_1"))
                    .filter(s -> s.contains("{") && s.contains("}"))
                    .reduce((ps,ns) -> ps.concat(" ").concat(ns))
                    .orElse("");
        } 

        return out;
    }

}
