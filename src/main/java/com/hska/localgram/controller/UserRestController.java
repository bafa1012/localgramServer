package com.hska.localgram.controller;

import com.google.common.io.Files;
import com.hska.localgram.dao.IAppUserDAO;
import com.hska.localgram.model.AppUser;
import com.hska.localgram.model.ImageContainer;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/")
public class UserRestController {

    @Autowired
    IAppUserDAO user;

    @RequestMapping(value = "/img", method = RequestMethod.GET,
                    produces = "application/json")
    public ResponseEntity<ImageContainer> getImage() {
        try {
            String[][] meta = new String[1][4];
            meta[0][0] = "image.jpg";
            meta[0][1] = "49";
            meta[0][2] = "8";
            meta[0][3] = "1";

            String[][] tags = new String[1][3];
            tags[0][0] = "Karlsruhe";
            tags[0][1] = "Test";
            tags[0][2] = "Tag";

            File file = new File("C:\\Users\\F\\Pictures\\wireshark.jpg");
            byte[] fileStr = Files.toByteArray(file);
            byte[][] images = new byte[1][fileStr.length];
            for (int i = 0; i < fileStr.length; ++i) {
                images[0][i] = fileStr[i];
            }
            ImageContainer container = new ImageContainer("description", meta, tags, images);

            return new ResponseEntity<>(container, HttpStatus.OK);
        } catch (IOException ex) {
            Logger.getLogger(UserRestController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
//
//    @RequestMapping(value = "/img", method = RequestMethod.POST,
//                    produces = "application/json", consumes = "application/json")
//    public ResponseEntity<ImageContainer> postImage(@RequestBody ImageContainer container) {
//
//        return new ResponseEntity<>(container, HttpStatus.OK);
//    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public String get() {
        return "<!doctype html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta http-equiv=\"content-type\" content=\"text/html\"; charset=UTF-8\">\n"
                + "<title>The Rest Service is up and running</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Rest Service is up and running</h1>\n"
                + "</body>\n"
                + "</html>";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @Secured("ROLE_USER")
    public ResponseEntity getLogin() {
        return new ResponseEntity("{\"login\": \"successful\"}", HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,
                    consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity registerUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "mail") String mail,
            @RequestParam(value = "password") String password) {
        if (user.getAppUserByMail(mail) != null || user.getAppUserByName(
                username) != null) {
            return new ResponseEntity("{\"register\": \"not successful\"}",
                                      HttpStatus.FORBIDDEN);
        }
        user.addAppUser(new AppUser().setMail(mail)
                .setName(username)
                .setPassword(password));
        return new ResponseEntity("{\"register\": \"successful\"}",
                                  HttpStatus.OK);
    }
}
