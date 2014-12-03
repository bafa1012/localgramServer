package com.hska.localgram.rest.api.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Container for the user data.
 * 
 * @author Fabian Bäuerlein
 */
public class UserData implements UserDetails {

    private static final long serialVersionUID = -6895116732233209817L;

    private Collection<? extends GrantedAuthority> authorities = null;
    private String userName;
    private String password;
    private boolean status;

    public UserData() {
        authorities = new ArrayList<GrantedAuthority>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> roles) {
        this.authorities = roles;
    }

    public void setAuthentication(boolean status) {
        this.status = status;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    @Override
    public String getUsername() {
        return this.userName;
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

}
