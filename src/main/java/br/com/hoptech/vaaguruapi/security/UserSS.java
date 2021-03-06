package br.com.hoptech.vaaguruapi.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.hoptech.vaaguruapi.enums.Roles;

public class UserSS implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String password;
	 
    public UserSS() {
	
    }
            
    public UserSS(Integer id, String email, String password) {
	super();
	this.id = id;
	this.email = email;
	this.password = password;
    }

    @Override
    public String getPassword() {
	return password;
    }

    @Override
    public String getUsername() {
	return email;
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }
    
    public Integer getId() {
	return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	return null;
    }
    
    public boolean hasRole(Roles role) {
	return getAuthorities().contains(new SimpleGrantedAuthority(role.getName()));
    }

}

