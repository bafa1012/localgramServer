package com.hska.localgram.controller;

import com.hska.localgram.model.Image;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
public class ImageRestControllerTest {
    
    public ImageRestControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addImage method, of class ImageRestController.
     */
    @Test
    public void testAddImage() {
        System.out.println("addImage");
        Image image = null;
        ImageRestController instance = new ImageRestController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.addImage(image);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteImage method, of class ImageRestController.
     */
    @Test
    public void testDeleteImage() {
        System.out.println("deleteImage");
        Long id = null;
        ImageRestController instance = new ImageRestController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.deleteImage(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImage method, of class ImageRestController.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        Long id = null;
        ImageRestController instance = new ImageRestController();
        Image expResult = null;
        Image result = instance.getImage(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImages method, of class ImageRestController.
     */
    @Test
    public void testGetImages() {
        System.out.println("getImages");
        ImageRestController instance = new ImageRestController();
        List<Image> expResult = null;
        List<Image> result = instance.getImages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImagesByUser method, of class ImageRestController.
     */
    @Test
    public void testGetImagesByUser() {
        System.out.println("getImagesByUser");
        Long userId = null;
        ImageRestController instance = new ImageRestController();
        List<Image> expResult = null;
        List<Image> result = instance.getImagesByUser(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImagesByTag method, of class ImageRestController.
     */
    @Test
    public void testGetImagesByTag() {
        System.out.println("getImagesByTag");
        Long tagID = null;
        ImageRestController instance = new ImageRestController();
        List<Image> expResult = null;
        List<Image> result = instance.getImagesByTag(tagID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImagesByGeoLocation method, of class ImageRestController.
     */
    @Test
    public void testGetImagesByGeoLocation() {
        System.out.println("getImagesByGeoLocation");
        double latitude = 0.0;
        double longitude = 0.0;
        int radius = 0;
        ImageRestController instance = new ImageRestController();
        List<Image> expResult = null;
        List<Image> result = instance.getImagesByGeoLocation(latitude, longitude,
                                                             radius);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateImage method, of class ImageRestController.
     */
    @Test
    public void testUpdateImage() {
        System.out.println("updateImage");
        Image image = null;
        ImageRestController instance = new ImageRestController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.updateImage(image);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
