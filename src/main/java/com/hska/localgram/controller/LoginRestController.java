/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hska.localgram.controller;

import com.hska.localgram.model.Login;
import com.hska.localgram.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author F
 */
@RestController
@RequestMapping("/login")
public class LoginRestController {

    @Autowired
    private IAppUserService service;
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity checkLogin(@RequestBody Login login) {
        if (service.checkLogin(login.getName(), login.getPassword()) != null) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
}
