package br.com.hoptech.vaaguruapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class InscriptionNewDTO implements Serializable  {
    private static final long serialVersionUID = 1L;

    //@NotNull(message="Preenchimento obrigatório") ver uma forma de validar com OR
    private Integer rower_id;
    private String rower_email;
    
    @NotNull(message="Preenchimento obrigatório")
    private Integer schedule_id;
    
    public InscriptionNewDTO() {	
    }

    public Integer getRower_id() {
        return rower_id;
    }

    public void setRower_id(Integer rower_id) {
        this.rower_id = rower_id;
    }

    public Integer getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(Integer schedule_id) {
        this.schedule_id = schedule_id;
    }
              
    public String getRower_email() {
	return rower_email;
    }
    
    public void setRower_email(String rower_email) {
	this.rower_email = rower_email;
    }
}
