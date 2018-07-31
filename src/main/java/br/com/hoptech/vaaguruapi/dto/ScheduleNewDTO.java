package br.com.hoptech.vaaguruapi.dto;

import java.util.Date;

public class ScheduleNewDTO {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;
    
    private Date date;
    private Integer numOfSeats;
    private Integer team_id;
    private String rowerResponsable_email;
    private String obs;
    
    public ScheduleNewDTO() {
	
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getRowerResponsable_email() {
        return rowerResponsable_email;
    }

    public void setRowerResponsable_email(String rowerResponsable_email) {
        this.rowerResponsable_email = rowerResponsable_email;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }
    
    
    
}
