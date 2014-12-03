package com.hska.localgram.controller;

import com.hska.localgram.model.Tag;
import com.hska.localgram.service.ITagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fabian Bäuerlein
 */
@RestController
@RequestMapping("/tag")
public class TagRestController {

    @Autowired
    private ITagService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addTag(@RequestBody Tag tag) {
        if (service.addTag(tag)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteTag(@RequestParam(value = "id") Long id) {
        if (service.deleteTag(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Tag getTag(@RequestParam(value = "id", required = false,
                                    defaultValue = "8") Long id) {
        return service.getTag(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Tag> getTags() {
        List<Tag> tags = service.getTags();
        return tags;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateTag(@RequestBody Tag tag) {
        if (service.updateTag(tag)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
}
