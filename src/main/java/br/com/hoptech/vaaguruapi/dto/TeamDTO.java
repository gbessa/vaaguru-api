package br.com.hoptech.vaaguruapi.dto;

import java.io.Serializable;

import br.com.hoptech.vaaguruapi.domain.Team;

public class TeamDTO implements Serializable  {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private String imageUrl;
    
    public TeamDTO() {	
    }
    
    public TeamDTO(Team obj) {
	id = obj.getId();
	name = obj.getName();
	description = obj.getDescription();
	imageUrl = obj.getImageUrl();
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
        
}
