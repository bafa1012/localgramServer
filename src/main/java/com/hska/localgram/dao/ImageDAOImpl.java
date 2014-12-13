package com.hska.localgram.dao;

import com.hska.localgram.model.Image;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Repository
public class ImageDAOImpl implements IImageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean addImage(Image image) {
        getCurrentSession()
                .save(image);
        return true;
    }

    @Override
    public boolean deleteImage(Long id) {
        Image image = getImage(id);
        if (image != null) {
            getCurrentSession()
                    .delete(image);
        }
        return getImage(id) == null;
    }

    @Override
    public Image getImage(Long id) {
        return (Image) getCurrentSession()
                .get(Image.class, id);
    }

    @Override
    public List<Image> getImages() {
        return getCurrentSession()
                .createQuery("from Image")
                .list();
    }

    @Override
    public List<Image> getImagesByUser(Long owner) {
        return getCurrentSession()
                .createQuery("from Image where owner_id = " + owner)
                .list();
    }

    // TODO implementation
    @Override
    public boolean updateImage(Image image) {
        Image imageToUpdate = getImage(image.getId());
        getCurrentSession()
                .update(imageToUpdate);
        Image imageUpdate = getImage(image.getId());
        return true;
    }

}
