package com.hska.localgram.dao;

import com.hska.localgram.model.AppUser;
import com.hska.localgram.model.Image;
import com.hska.localgram.model.Tag;
import java.util.List;
import org.hibernate.HibernateException;
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
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    @Override
    public Image addImage(Image image) {
        getCurrentSession()
                .save(image);
        return getImageByFileNameAndUser(image.getFile_name(), image.getOwner());
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
    public Image getImageByFileNameAndUser(String fileName, AppUser user) {
        List<Image> images = getImages();
        for (Image image : images) {
            if (image.getOwner()
                    .equals(user)
                    && image.getFile_name()
                    .equals(fileName)) {
                return image;
            }
        }
        return null;
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

    @Override
    public List<Image> getImagesByTag(Tag tag) {
        List<Image> images = getImages();
        for (Image i : images) {
            if (!i.getTag_list().contains(tag)) {
                images.remove(i);
            }
        }
        return images;
    }

    @Override
    public List<Image> getImagesByGeoLocation(double latitude, double longitude,
                                              int radius) {
        return getCurrentSession()
                .createSQLQuery(
                        "SELECT * FROM image WHERE acos(sin(:latitude) * sin(latitude) + cos(:latitude) * cos(latitude) * cos(longitude - (:longitude))) * 6371 <= :radius")
                .setParameter("latitude", "" + latitude)
                .setParameter("longitude", "" + longitude)
                .setParameter("radius", "" + radius)
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
