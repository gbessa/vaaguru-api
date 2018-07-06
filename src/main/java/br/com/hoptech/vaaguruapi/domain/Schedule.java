package br.com.hoptech.vaaguruapi.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Schedule {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Date date;
    private Integer status;
    private Integer numOfSeats;
    private String obs;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "rower_id")
    private Rower rower;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    
    @JsonIgnore
    @OneToMany(mappedBy = "schedule")
    private List<Inscription> inscriptions = new ArrayList<>();
    
    public Schedule() {	
    }

    public Schedule(Integer id, Date date, Integer status, Integer numOfSeats, Team team, Rower rower, String obs) {
	super();
	this.id = id;
	this.date = date;
	this.status = status;
	this.numOfSeats = numOfSeats;
	this.team = team;
	this.rower = rower;
	this.obs = obs;
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

    public Rower getRowerResponsable() {
        return rower;
    }

    public void setRowerResponsable(Rower rowerResponsable) {
        this.rower = rowerResponsable;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Schedule other = (Schedule) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }
        
}
