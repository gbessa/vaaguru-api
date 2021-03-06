package br.com.hoptech.vaaguruapi.dto;

import java.io.Serializable;

import br.com.hoptech.vaaguruapi.domain.Invitation;

public class InvitationDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String invited_email;
    
    private String inviter_email;
    
    private Integer team_id;
    private String team_name;
    
    private Integer status;
    
    public InvitationDTO() {
    }
    
    public InvitationDTO(Invitation obj) {
	this.id = obj.getId();
	this.invited_email = obj.getInvitedEmail();
	this.inviter_email = obj.getInviter().getEmail();
	this.team_id = obj.getTeam().getId();
	this.team_name = obj.getTeam().getName();
	this.status = obj.getStatus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public String getInvited_email() {
        return invited_email;
    }

    public void setInvited_email(String invited_email) {
        this.invited_email = invited_email;
    }

    public String getInviter_email() {
        return inviter_email;
    }

    public void setInviter_email(String inviter_email) {
        this.inviter_email = inviter_email;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
    
}
