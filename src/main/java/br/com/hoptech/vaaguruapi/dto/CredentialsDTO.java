package br.com.hoptech.vaaguruapi.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CredentialsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private String facebookToken;

    public CredentialsDTO() {

    }

    public CredentialsDTO(String email, String password, String facebookToken) {
	super();
	this.email = email;
	this.password = password;
	this.facebookToken = facebookToken;
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

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    
}
