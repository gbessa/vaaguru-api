package br.com.hoptech.vaaguruapi.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.hoptech.vaaguruapi.domain.Team;

public class TeamDTO implements Serializable  {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private String imageUrl;
    
    @JsonIgnore
    private String owner_email;
    
    private List<RowerDTO> owners;
    
    private List<RowerDTO> members;
    
    public TeamDTO() {	
    }
    
    public TeamDTO(Team obj) {
	id = obj.getId();
	name = obj.getName();
	description = obj.getDescription();
	imageUrl = obj.getImageUrl();
	owners = obj.getOwners().stream().map(owner -> new RowerDTO(owner)).collect(Collectors.toList());
	members = obj.getMembers().stream().map(member -> new RowerDTO(member)).collect(Collectors.toList());
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

    public List<RowerDTO> getOwners() {
        return owners;
    }

    public void setOwners(List<RowerDTO> owners) {
        this.owners = owners;
    }

    public List<RowerDTO> getMembers() {
        return members;
    }

    public void setMembers(List<RowerDTO> members) {
        this.members = members;
    }

    public String getOwner_email() {
        return owner_email;
    }

    @JsonProperty
    public void setOwner_email(String owner_email) {
        this.owner_email = owner_email;
    }
                
}
