/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hska.localgram.controller;

import com.hska.localgram.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hska.localgram.service.IAppUserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author F
 */
@RestController
@RequestMapping("/user")
public class AppUserRestController {

    @Autowired
    private IAppUserService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addAppUser(@RequestBody AppUser user) {
        if (service.addAppUser(user)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteAppUser(@RequestParam(value = "id") Long id) {
        if (service.deleteAppUser(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public AppUser getAppUser(@RequestParam(value = "id", required = true) Long id) {
        return service.getAppUser(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<AppUser> getAppUsers() {
        List<AppUser> users = service.getAppUsers();
        return users;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateAppUser(@RequestBody AppUser user) {
        if (service.updateAppUser(user)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
}
