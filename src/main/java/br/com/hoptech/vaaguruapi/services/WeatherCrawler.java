package br.com.hoptech.vaaguruapi.services;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WeatherCrawler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherCrawler.class.getName());
   
    public void fetchTest() {
	
	String URL = "https://www.windguru.cz/531525";
	
	 Document doc;
	try {
	    //Fetch the HTML code
	    doc = Jsoup.connect(URL).get();
	    LOGGER.info(doc.title());
	    
	    Elements eles = doc.select("#div_wgfcst0");
	    LOGGER.info(eles.html());
	    LOGGER.info(String.valueOf(eles.size()));
	    for (Element ele : eles) {
		LOGGER.info(ele.text());
	    }
	    
	} catch (IOException e) {
	    LOGGER.error("For '" + URL + "': " + e.getMessage());
	}
	
    }
    
    
    
    //Fetch the HTML code
//    Document doc = Jsoup.connect(URL).get();
    
//     (doc.title());
    
//    Elements newsHeadlines = doc.select("#mp-itn b a");
//    for (Element headline : newsHeadlines) {
//      log("%s\n\t%s", 
//        headline.attr("title"), headline.absUrl("href"));
//    }

}
