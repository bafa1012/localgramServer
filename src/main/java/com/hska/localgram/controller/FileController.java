package com.hska.localgram.controller;

import com.hska.localgram.util.Constants;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Controller
@Secured("ROLE_USER")
public class FileController {

    /**
     * Upload for multiple files.
     *
     * @param user owner of the files
     * @param fileNames array of file names
     * @param files array of multipart files
     * @param request
     *
     * @return HTTP status code
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity uploadMultipleFileHandler(
            @RequestParam("user") String user,
            @RequestParam("name") String[] fileNames,
            @RequestParam("file") MultipartFile[] files,
            HttpServletRequest request) {

        // Return HTTP conflict if length of the file array and the file name array differ in length.
        if (files.length != fileNames.length) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String name = fileNames[i];
            try {
                byte[] bytes = file.getBytes();

                // get absolute path of the application
                ServletContext context = request.getServletContext();
                String appPath = context.getRealPath("");
                System.out.println("appPath = " + appPath);

                // Find or create the user directory
                String rootPath = Constants.FILE_ROOT_PATH;
                File dir = new File(appPath + File.separator + user);
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
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/download/{user_name}")
    public void getFilesByOwner() {

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
        System.out.println("appPath = " + appPath);

        // construct the complete absolute path of the file
        String fullPath = appPath + userName + "\\" + fileName;
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

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
