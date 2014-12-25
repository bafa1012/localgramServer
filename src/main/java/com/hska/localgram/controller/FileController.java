package com.hska.localgram.controller;

import com.hska.localgram.dao.IAppUserDAO;
import com.hska.localgram.dao.IImageDAO;
import com.hska.localgram.dao.ITagDAO;
import com.hska.localgram.model.AppUser;
import com.hska.localgram.model.Image;
import com.hska.localgram.model.ImageContainer;
import com.hska.localgram.model.Tag;
import com.hska.localgram.util.Constants;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Controller
@Secured("ROLE_USER")
public class FileController {

    @Autowired
    IAppUserDAO userDAO;

    @Autowired
    ITagDAO tagDAO;

    @Autowired
    IImageDAO imageDAO;

//    @RequestMapping(value = "/img", method = RequestMethod.POST,
//                    produces = "application/json", consumes = "application/json")
//    public ResponseEntity<ImageContainer> postImage(@RequestBody ImageContainer container) {
//
//        return new ResponseEntity<>(container, HttpStatus.OK);
//    }
    /**
     * Upload for multiple files.
     *
     * @param container
     * @param request
     *
     * @return ImageContainer
     */
    @RequestMapping(value = "/img", method = RequestMethod.POST,
                    produces = "application/json", consumes = "application/json")
    public ResponseEntity<ImageContainer> postImage(
            @RequestBody ImageContainer container,
            HttpServletRequest request) {

        // Return HTTP conflict if length of the file array and the file name array differ in length.
        if (container.getBitmaps().length != container.getMeta().length || container
                .getBitmaps().length != container.getTags().length) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        for (int i = 0; i < container.getBitmaps().length; i++) {
            String name = container.getMeta()[i][0];
            // create meta object for image
            double latitude;
            double longitude;
            long userID;
            AppUser user;
            try {
                latitude = Double.valueOf(container.getMeta()[i][1]);
                longitude = Double.valueOf(container.getMeta()[i][2]);
                userID = Long.valueOf(container.getMeta()[i][3]);
                user = userDAO.getAppUser(userID);
                if (user == null) {
                    throw new Exception("user not found");
                }
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
            }
            Image image = new Image();
            image.setFile_name(container.getMeta()[i][0]);
            image.setLatitude(latitude);
            image.setLongitude(longitude);
            image.setOwner(user);
            HashSet<Tag> tagSet = new HashSet<>();
            for (String string : container.getTags()[i]) {
                Tag tag = tagDAO.getTagByContent(string);
                if (tag == null) {
                    tag = new Tag();
                }
                tag.addImage(image);
                tag.setTag(string);
                tag = tagDAO.addTag(tag, image);
                tagSet.add(tag);
            }
            image.addTagSet(tagSet);
            try {
                image = imageDAO.addImage(image);
            }
            catch (Exception e) {
                // ToDO Exception Handling
            }
            try {
                byte[] bytes = container.getBitmaps()[i];

                // get absolute path of the application
                ServletContext context = request.getServletContext();
                String appPath = context.getRealPath("");

                // Find or create the user directory
                File dir = new File(appPath + File.separator + container.getMeta()[i][3]);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Create the file on the server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                imageDAO.deleteImage(image.getId());
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/download/{user_name}/{file_name:.+}",
                    method = RequestMethod.GET)
    public void download(
            @PathVariable("user_name") String userName,
            @PathVariable("file_name") String fileName,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        // get absolute path of the application
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");

        // construct the complete absolute path of the file
        String fullPath = appPath + File.separator + userName + File.separator + fileName;
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                                           downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[Constants.MAX_UPLOAD_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outStream.close();
    }
}
