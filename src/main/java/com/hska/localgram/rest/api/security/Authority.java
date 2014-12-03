package com.hska.localgram.rest.api.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Authority class to manage the authorities.
 *
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 *
 */
public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = 7989597359923731104L;

    String authority = null;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authorityName) {
        this.authority = authorityName;
    }
}
