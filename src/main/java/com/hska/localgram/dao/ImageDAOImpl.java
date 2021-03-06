package com.hska.localgram.dao;

import com.hska.localgram.model.AppUser;
import com.hska.localgram.model.Image;
import com.hska.localgram.model.Tag;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Repository
public class ImageDAOImpl implements IImageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private EntityManager em;
    
    @PersistenceContext
    void setEntityManager(EntityManager entityManager)
    {
        this.em = entityManager;
    }

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
    @Transactional
    public Image addImage(Image newImage) {
        Image image = getImageByFileNameAndUser(newImage.getFile_name(), newImage.getOwner());
        if (image != null)
            image.addTagSet(newImage.getTag_list());
        else
            image = newImage;
        try {
            getCurrentSession().merge(image);
//            if (image.getId() == null) {
//                getCurrentSession().merge(image);
//            }
        }
        catch (Exception e) {
            // ToDo Exception handling
        }
        return image;
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
            if (image.getFile_name().equals(fileName)) {
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
        SQLQuery query = getCurrentSession()
                .createSQLQuery(
                        "SELECT * FROM image WHERE acos(sin(:latitude) * sin(latitude) + cos(:latitude) * cos(latitude) * cos(longitude - (:longitude))) * 6371 <= :radius");
        query.setParameter("latitude", "" + latitude)
                .setParameter("longitude", "" + longitude)
                .setParameter("radius", "" + radius);
        query.addEntity(Image.class);
        List<Image> images = query.list();
//        List<Image> images = new ArrayList<>();
//        for (Object o : rows) {
//            Map map = (Map) o;
//            Image image = new Image();
//            image.setFile_name((String) map.get((Object) "file_name"));
//            image.setId((Long) map.get((Object) "id"));
//            image.setLatitude((double) map.get((Object) "latitude"));
//            image.setLongitude((double) map.get((Object) "longitude"));
//            images.add(image);
//        }
        return images;
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
