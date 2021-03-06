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
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@RestController
@RequestMapping("/image")
@Secured("ROLE_USER")
public class ImageRestController {

    @Autowired
    private IImageService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addImage(@RequestBody Image image) {
        if (service.addImage(image) != null) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteImage(@RequestParam(value = "id") Long id) {
        if (service.deleteImage(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getImage(@RequestParam(value = "id", required = false,
                                        defaultValue = "8") Long id) {
        ResponseEntity response = new ResponseEntity(service.getImage(id), HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getImages() {
        List<Image> images = service.getImages();
        ResponseEntity response = new ResponseEntity(images, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
    public ResponseEntity getImagesByUser(@PathVariable("user_id") Long userId) {
        List<Image> images = service.getImagesByUser(userId);
        ResponseEntity response = new ResponseEntity(images, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/tag/{tag_id}", method = RequestMethod.GET)
    public ResponseEntity getImagesByTag(@PathVariable("tag_id") Long tagID) {
        List<Image> images = service.getImagesByTag(tagID);
        ResponseEntity response = new ResponseEntity(images, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/geo/{latitude:.+}/{longitude:.+}/{radius}",
                    method = RequestMethod.GET)
    public ResponseEntity getImagesByGeoLocation(
            @PathVariable("latitude") double latitude,
            @PathVariable("longitude") double longitude,
            @PathVariable("radius") int radius) {
        List<Image> images = service.getImagesByGeoLocation(latitude, longitude,
                                                            radius);
        ResponseEntity response = new ResponseEntity(images, HttpStatus.OK);
        return response;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateImage(@RequestBody Image image) {
        if (service.updateImage(image)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
}
