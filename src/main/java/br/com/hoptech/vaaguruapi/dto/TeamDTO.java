package br.com.hoptech.vaaguruapi.dto;

import java.io.Serializable;
import java.util.List;

import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.domain.Team;

public class TeamDTO implements Serializable  {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private String imageUrl;
    
    private List<Rower> owners;
    
    private List<Rower> members;
    
    public TeamDTO() {	
    }
    
    public TeamDTO(Team obj) {
	id = obj.getId();
	name = obj.getName();
	description = obj.getDescription();
	imageUrl = obj.getImageUrl();
	owners = obj.getOwners();
	members = obj.getMembers();
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

    public List<Rower> getOwners() {
        return owners;
    }

    public void setOwners(List<Rower> owners) {
        this.owners = owners;
    }

    public List<Rower> getMembers() {
        return members;
    }

    public void setMembers(List<Rower> members) {
        this.members = members;
    }
                
}
