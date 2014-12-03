package com.hska.localgram.rest.api.security;

import com.hska.localgram.model.AppUser;
import com.hska.localgram.service.IAppUserService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Get the user information from the database.
 * 
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 *
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IAppUserService service;

    @Override
    public UserDetails loadUserByUsername(String userName) throws
            UsernameNotFoundException {
        UserData userData = new UserData();
        // Get the matching user from database
        AppUser user = service.getAppUserByName(userName);
        if (user == null) {
            return null;
        }
        userData.setAuthentication(true);
        userData.setPassword(user.getPassword());
        Collection<Authority> roles = new ArrayList<>();
        Authority role = new Authority();
        role.setAuthority("ROLE_USER");
        roles.add(role);
        userData.setAuthorities(roles);
        return userData;
    }
}
