package br.com.hoptech.vaaguruapi.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rower implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    
    @Column(unique=true)
    private String email;
    
    private String name;
    private String phone;
    private Boolean isSteerer;
    private String imageUrl;
    
    @JsonIgnore	
    private String password;
    
    @JsonIgnore
    @OneToMany(mappedBy = "rower", cascade=CascadeType.ALL)
    private List<Inscription> inscriptions = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "rower", cascade=CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();
    
    @JsonIgnore
//    @ManyToMany(cascade= CascadeType.MERGE)
//    @JoinTable(name = "OWNER_TEAM", joinColumns = @JoinColumn(name = "rower_id"), inverseJoinColumns = @JoinColumn(name = "team_id"))
    @ManyToMany(mappedBy="owners")
    private List<Team> teamsOwner = new ArrayList<>();
    
    @JsonIgnore
//    @ManyToMany(cascade= CascadeType.MERGE)
//    @JoinTable(name = "MEMBER_TEAM", joinColumns = @JoinColumn(name = "rower_id"), inverseJoinColumns = @JoinColumn(name = "team_id"))
    @ManyToMany(mappedBy="members")
    private List<Team> teamsMember = new ArrayList<>();
    
//    @JsonIgnore
//    @OneToMany(mappedBy="invited")
//    private List<Invitation> invitations = new ArrayList<>();
    
    //private PaddleType paddle;
    //private Integer level;
    //private fixedDays: Enum
    //private fixedTime: Enum
    //private commonPosition: Enum

    public Rower() {	
    }
    
    public Rower(Integer id, String name, String email, String phone, Boolean isSteerer, String imageUrl, String password) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.phone = phone;
	this.isSteerer = isSteerer;
	this.imageUrl = imageUrl;
	this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsSteerer() {
        return isSteerer;
    }

    public void setIsSteerer(Boolean isSteerer) {
        this.isSteerer = isSteerer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getPassword() {
	return password;
    }
    
    public void setPassword(String password) {
	this.password = password;
    }
   
    public List<Team> getTeamsOwner() {
        return teamsOwner;
    }

    public void setTeamsOwner(List<Team> teamsOwner) {
        this.teamsOwner = teamsOwner;
    }

    public List<Team> getTeamsMember() {
        return teamsMember;
    }

    public void setTeamsMember(List<Team> teamsMember) {
        this.teamsMember = teamsMember;
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
	Rower other = (Rower) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }
    
}
