package com.hska.localgram.controller;

import com.hska.localgram.dao.IAppUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@RestController
@RequestMapping("/")
public class UserRestController {
    
    @Autowired
    IAppUserDAO user;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @Secured("ROLE_USER")
    public ResponseEntity getLogin() {
        return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity registerUser(@RequestParam(value = "username") String username,
                                       @RequestParam(value = "mail") String mail,
                                       @RequestParam(value = "password") String password ) {
        if (user.getAppUserByMail(mail) != null || user.getAppUserByName(username) != null) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
