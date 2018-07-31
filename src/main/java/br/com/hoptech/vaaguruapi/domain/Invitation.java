package br.com.hoptech.vaaguruapi.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Invitation {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date time;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;
    
    @ManyToOne
    @JoinColumn(name="rower_id")
    private Rower rower;
    
//    @ManyToOne
//    @JoinColumn(name="inviter_id")
//    private Rower inviter;
    
    private Integer status;
        
    
    public Invitation() {
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


    public Team getTeam() {
        return team;
    }


    public void setTeam(Team team) {
        this.team = team;
    }


    public Rower getRower() {
        return rower;
    }


    public void setRower(Rower rower) {
        this.rower = rower;
    }


//    public Rower getInviter() {
//        return inviter;
//    }
//
//
//    public void setInviter(Rower inviter) {
//        this.inviter = inviter;
//    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
    
}
