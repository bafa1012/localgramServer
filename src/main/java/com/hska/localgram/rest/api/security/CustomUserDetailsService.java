package com.hska.localgram.rest.api.security;

import com.hska.localgram.model.AppUser;
import com.hska.localgram.service.IAppUserService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * CustomUserDetailsService provides the connection point to external data
 * source
 *
 * @author malalanayake
 *
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IAppUserService service;
    private String USER_ADMIN = "admin";
    private String PASS_ADMIN = "adminpass";

    private String USER = "user";
    private String PASS = "userpass";

    @Override
    public UserDetails loadUserByUsername(String authentication) throws
            UsernameNotFoundException {
        CustomUserData customUserData = new CustomUserData();
        // You can talk to any of your user details service and get the
        // authentication data and return as CustomUserData object then spring
        // framework will take care of the authentication
        AppUser user = service.getAppUserByName(authentication);
        if (user == null) {
            return null;
        }
        customUserData.setAuthentication(true);
        customUserData.setPassword(user.getPassword());
        Collection<CustomRole> roles = new ArrayList<CustomRole>();
        CustomRole customRole = new CustomRole();
        customRole.setAuthority("ROLE_USER");
        roles.add(customRole);
        customUserData.setAuthorities(roles);
        return customUserData;
    }

    /**
     * Custom Role class for manage the authorities
     *
     * @author malalanayake
     *
     */
    private class CustomRole implements GrantedAuthority {

        String role = null;

        @Override
        public String getAuthority() {
            return role;
        }

        public void setAuthority(String roleName) {
            this.role = roleName;
        }

    }

}
