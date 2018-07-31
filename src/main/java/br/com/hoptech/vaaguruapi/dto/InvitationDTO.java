package br.com.hoptech.vaaguruapi.dto;

import br.com.hoptech.vaaguruapi.domain.Invitation;

public class InvitationDTO {
    private static final long serialVersionUID = 1L;

    private String invited_email;
    
    private String inviter_email;
    
    private Integer team_id;
    
    private Integer status;
    
    public InvitationDTO() {
    }
    
    public InvitationDTO(Invitation obj) {
	//this.invited_email = obj.
	//this.inviter_email = obj.
	this.team_id = obj.getTeam().getId();
	this.status = obj.getStatus();
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

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
    
}
