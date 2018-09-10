package br.com.hoptech.vaaguruapi.services;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    
    public String getWeatherInfo(Integer cityId, Date date) {
	return "I'll rain";
    }
    
}
