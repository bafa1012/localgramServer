package com.hska.localgram.controller;

import com.hska.localgram.model.Image;
import com.hska.localgram.model.ImageTagVote;
import com.hska.localgram.model.Tag;
import com.hska.localgram.service.IImageTagVoteService;
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
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@RestController
@RequestMapping("/tag")
public class TagRestController {

    @Autowired
    private ITagService service;

    @Autowired
    private IImageTagVoteService vote;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addTag(@RequestBody Tag tag, @RequestBody Image image) {
        if (service.addTag(tag, image) != null) {
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
    public ResponseEntity getTag(@RequestParam(value = "id", required = false,
                                    defaultValue = "8") Long id) {
        ResponseEntity response = new ResponseEntity(service.getTag(id), HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public ResponseEntity voteTag(@RequestParam(value = "id") Long id) {
        ImageTagVote v = vote.vote(id);
        ResponseEntity response = new ResponseEntity(v.getTag(), HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getTags() {
        ResponseEntity response = new ResponseEntity(service.getTags(), HttpStatus.OK);
        return response;
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
