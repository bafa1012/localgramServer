/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hska.localgram.controller;

import com.hska.localgram.model.Image;
import com.hska.localgram.service.IImageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.annotation.Secured;

/**
 *
 * @author F
 */
@RestController
@RequestMapping("/image")
@Secured("ROLE_USER")
public class ImageRestController {

    @Autowired
    private IImageService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addImage(@RequestBody Image image) {
        if (service.addImage(image)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteImage(@RequestParam(value = "id") Long id) {
        if (service.deleteImage(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Image getImage(@RequestParam(value = "id", required = false, defaultValue = "8") Long id) {
        return service.getImage(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Image> getImages() {
        List<Image> images = service.getImages();
        return images;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateImage(@RequestBody Image image) {
        if (service.updateImage(image)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
}
