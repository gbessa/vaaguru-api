package br.com.hoptech.vaaguruapi.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RowerNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotEmpty(message="The name shoud be informed")
    private String name;
    
    @NotEmpty(message="The email shoud be informed")
    @Email(message = "Invalid Email")
    private String email;
    
    @NotEmpty(message="The password shoud be informed")
    private String password;
    
    private String phone;
    private Boolean isSteerer;
    private String imageUrl;
    
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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
