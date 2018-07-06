package br.com.hoptech.vaaguruapi.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.hoptech.vaaguruapi.domain.Inscription;
import br.com.hoptech.vaaguruapi.domain.Schedule;
import br.com.hoptech.vaaguruapi.domain.Team;

public class ScheduleDTO implements Serializable  {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;
    
    private Integer status;
    private Integer numOfSeats;
    private String obs;
    
    private RowerDTO rowerResposable;

    private TeamDTO team;
    
    private Integer numOfInscriptions;
    
    public ScheduleDTO() {
	
    }
    
    public ScheduleDTO(Schedule obj) {
	this.id = obj.getId();
	this.date = obj.getDate();
	this.status = obj.getStatus();
	this.numOfSeats = obj.getNumOfSeats();
	this.obs = obj.getObs();
	this.rowerResposable = new RowerDTO(obj.getRowerResponsable());
	this.team = new TeamDTO(obj.getTeam());
	this.numOfInscriptions = obj.getInscriptions().size();	
    }
     
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(Integer numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public RowerDTO getRowerResposable() {
	return rowerResposable;
    }
    
    public void setRowerResposable(RowerDTO rowerResposable) {
	this.rowerResposable = rowerResposable;
    }
    
    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public Integer getNumOfInscriptions() {
        return numOfInscriptions;
    }

    public void setNumOfInscriptions(Integer numOfInscriptions) {
        this.numOfInscriptions = numOfInscriptions;
    }
    
}
