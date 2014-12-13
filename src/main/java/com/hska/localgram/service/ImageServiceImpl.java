package com.hska.localgram.service;

import com.hska.localgram.dao.IImageDAO;
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

    @Override
    public boolean addImage(Image image) {
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
    public List<Image> getImages() {
        return imageDAO.getImages();
    }

    @Override
    public List<Image> getImagesByUser(Long owner) {
        return imageDAO.getImagesByUser(owner);
    }

    @Override
    public boolean updateImage(Image image) {
        return imageDAO.updateImage(image);
    }

}
