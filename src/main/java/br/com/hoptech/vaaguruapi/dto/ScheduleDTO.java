package br.com.hoptech.vaaguruapi.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.hoptech.vaaguruapi.domain.Inscription;

public class ScheduleDTO implements Serializable  {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date time;
    
    private String obs;
     
    public ScheduleDTO() {
	
    }
}
