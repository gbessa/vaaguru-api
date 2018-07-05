package br.com.hoptech.vaaguruapi.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.hoptech.vaaguruapi.domain.Inscription;

public class InscriptionDTO implements Serializable  {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date time;
    
    private Integer rower_id;
    private Integer schedule_id;
    
    public Integer getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(Integer schedule_id) {
        this.schedule_id = schedule_id;
    }

    public InscriptionDTO() {	
    }
    
    public InscriptionDTO(Inscription obj) {
	this.id = obj.getId();
	this.time = obj.getTime();
	this.rower_id = obj.getRower().getId();
	this.schedule_id = obj.getSchedule().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getRower_id() {
	return rower_id;
    }

    public void setRower_id(Integer rower_id) {
	this.rower_id = rower_id;
    }
            
}
