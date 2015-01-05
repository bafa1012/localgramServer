package com.hska.localgram.controller;

import com.hska.localgram.model.AppUser;
import com.hska.localgram.model.Image;
import com.hska.localgram.model.Comment;
import com.hska.localgram.service.ICommentService;
import com.hska.localgram.service.IImageTagVoteService;
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
 *
 * @author Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@RestController
@RequestMapping("/comment")
public class CommentRestController {

    @Autowired
    private ICommentService service;

    @Autowired
    private IImageTagVoteService vote;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addComment(@RequestBody Comment comment) {
        if (service.addComment(comment) != null) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@RequestParam(value = "id") Long id) {
        if (service.deleteComment(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getComment(@RequestParam(value = "id") Long id) {
        ResponseEntity response = new ResponseEntity(service.getComment(id), HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity getCommentsByUserName(@RequestParam(value = "userID") Long id) {
        List<Comment> comments = service.getComments();
        for (Comment comment : comments) {
            if (!comment.getUser().getId().equals(id)) {
                comments.remove(comment);
            }
        }
        ResponseEntity response = new ResponseEntity(comments, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value="/image", method = RequestMethod.GET)
    public ResponseEntity getCommentsByImage(@RequestParam(value = "imageID") Long id) {
        List<Comment> comments = service.getComments();
        for (Comment comment : comments) {
            if (!comment.getImage().getId().equals(id)) {
                comments.remove(comment);
            }
        }
        ResponseEntity response = new ResponseEntity(comments, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getComments() {
        List<Comment> comments = service.getComments();
        ResponseEntity response = new ResponseEntity(comments, HttpStatus.OK);
        return response;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateComment(@RequestBody Comment comment) {
        if (service.updateComment(comment) != null) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
}
