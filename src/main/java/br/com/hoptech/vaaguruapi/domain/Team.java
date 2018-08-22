package br.com.hoptech.vaaguruapi.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Team implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique=true)
    private String name;
    private String description;
    private String imageUrl;
    
    @JsonIgnore
    @OneToMany(mappedBy = "team")
    private List<Schedule> schedules = new ArrayList<>();
    
    @JsonIgnore
    @ManyToMany(mappedBy = "teamsOwner")
    private List<Rower> owners = new ArrayList<>();   

    @JsonIgnore
    @ManyToMany(mappedBy = "teamsMember")
    private List<Rower> members = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy="team")
    private List<Invitation> invitations = new ArrayList<>();
    
    public Team() {	
    }

    public Team(Integer id, String name, String description, String imageUrl) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
	this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
    
    public List<Rower> getMembers() {
	return members;
    }
    
    public void setMembers(List<Rower> members) {
	this.members = members;
    }
    
    public List<Rower> getOwners() {
	return owners;
    }
    
    public void setOwners(List<Rower> owners) {
	this.owners = owners;
    }

   
    
}
