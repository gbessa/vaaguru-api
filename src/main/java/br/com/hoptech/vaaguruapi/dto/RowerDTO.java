package br.com.hoptech.vaaguruapi.dto;

import java.io.Serializable;

import br.com.hoptech.vaaguruapi.domain.Rower;

public class RowerDTO implements Serializable  {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String name;
    private String email;
    private String phone;
    private Boolean isSteerer;
    private String imageUrl;
    
    public RowerDTO() {	
    }

    public RowerDTO(Rower obj) {
	this.id = obj.getId();
	this.name = obj.getName();
	this.email = obj.getEmail();
	this.phone = obj.getPhone();
	this.isSteerer = obj.getIsSteerer();
	this.imageUrl = obj.getImageUrl();
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
    
    
    
}
