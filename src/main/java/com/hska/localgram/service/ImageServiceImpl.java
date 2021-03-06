package com.hska.localgram.service;

import com.hska.localgram.dao.IImageDAO;
import com.hska.localgram.dao.ITagDAO;
import com.hska.localgram.model.AppUser;
import com.hska.localgram.model.Image;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Image service class.
 *
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Service
@Transactional
public class ImageServiceImpl implements IImageService {

    @Autowired
    private IImageDAO imageDAO;
    
    @Autowired
    private ITagDAO tagDAO;

    @Override
    public Image addImage(Image image) {
        return imageDAO.addImage(image);
    }

    @Override
    public boolean deleteImage(Long id) {
        return imageDAO.deleteImage(id);
    }

    @Override
    public Image getImage(Long id) {
        return imageDAO.getImage(id);
    }

    @Override
    public Image getImageByFileNameAndUser(String fileName, AppUser user) {
        return imageDAO.getImageByFileNameAndUser(fileName, user);
    }

    @Override
    public List<Image> getImages() {
        return imageDAO.getImages();
    }

    @Override
    public List<Image> getImagesByUser(Long owner) {
        return imageDAO.getImagesByUser(owner);
    }

    @Override
    public List<Image> getImagesByTag(Long tag) {
        return imageDAO.getImagesByTag(tagDAO.getTag(tag));
    }
    
    @Override
    public List<Image> getImagesByGeoLocation(double latitude, double longitude, int radius) {
        return imageDAO.getImagesByGeoLocation(latitude, longitude, radius);
    }

    @Override
    public boolean updateImage(Image image) {
        return imageDAO.updateImage(image);
    }

}
