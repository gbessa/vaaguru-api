package br.com.hoptech.vaaguruapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class InscriptionNewDTO implements Serializable  {
    private static final long serialVersionUID = 1L;

    @NotNull(message="Preenchimento obrigatório")
    private Integer rower_id;
    
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
              
}
