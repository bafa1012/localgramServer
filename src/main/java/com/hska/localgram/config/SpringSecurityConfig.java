package com.hska.localgram.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Initializes and configures spring security.
 * 
 * @author Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Configuration
@ImportResource({"classpath:webSecurityConfig.xml"})
@ComponentScan("com.hska.localgram.rest.api.security")
public class SpringSecurityConfig {

    public SpringSecurityConfig() {
        super();
    }

}
