package com.hska.localgram.controller;

import com.hska.localgram.dao.IAppUserDAO;
import com.hska.localgram.dao.IImageDAO;
import com.hska.localgram.dao.ITagDAO;
import com.hska.localgram.model.AppUser;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
public class FileControllerTest {

    private static AppUser user = new AppUser().setId(Long.valueOf(1))
            .setMail("test@test.de")
            .setName("test")
            .setPassword("passwort");

    @Mock
    private IAppUserDAO userDAO;

    @Mock
    ITagDAO tagDAO;

    @Mock
    IImageDAO imageDAO;

    // Testing instance, mocked `resource` should be injected here 
    @InjectMocks
    FileController instance = new FileController();

    public FileControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        // Initialize mocks created above
        MockitoAnnotations.initMocks(this);
        // Change behaviour of `resource`
        when(userDAO.getAppUser(Long.valueOf(1))).thenReturn(user);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of uploadMultipleFileHandler method, of class FileController.
     */
    @Test
    public void testUploadMultipleFileHandler() {
        System.out.println("uploadMultipleFileHandler");
        String[][] meta = new String[1][4];
        meta[0][0] = "ks.pdf";
        meta[0][1] = "49.0124289";
        meta[0][2] = "8.4062799";
        meta[0][3] = "1";
        String[][] tags = new String[1][3];
        tags[0][1] = "ks";
        tags[0][1] = "stupide";
        tags[0][1] = "langweilig";
        MultipartFile[] files = new MultipartFile[1];
        File file = new File(
                "C:\\Users\\F\\Documents\\hska\\CF6F8D9666CCABBDB63A81F0DE69688E.pdf");
        DiskFileItem fileItem = new DiskFileItem("file", "text/plain", false,
                                                 file.getName(), (int) file
                                                 .length(), file.getParentFile());
        try {
            fileItem.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(FileControllerTest.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        files[0] = new CommonsMultipartFile(fileItem);
        HttpServletRequest request = new MockHttpServletRequest();
        ResponseEntity expResult = new ResponseEntity(HttpStatus.OK);
        ResponseEntity result = instance.uploadMultipleFileHandler(meta, tags,
                                                                   files,
                                                                   request);
        assertEquals(expResult, result);
    }
}
