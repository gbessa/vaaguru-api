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
    
    private RowerDTO rower;
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
	this.rower = new RowerDTO(obj.getRower());
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

    public RowerDTO getRower() {
	return rower;
    }

    public void setRower(RowerDTO rower) {
	this.rower = rower;
    }
            
}
