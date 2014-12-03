/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hska.localgram.dao;

import com.hska.localgram.model.Image;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author F
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
        getCurrentSession().save(image);
        return true;
    }

    @Override
    public boolean deleteImage(Long id) {
        Image image = getImage(id);
        if (image != null) {
            getCurrentSession().delete(image);
        }
        return getImage(id) == null;
    }

    @Override
    public Image getImage(Long id) {
        return (Image) getCurrentSession().get(Image.class, id);
    }

    @Override
    public List<Image> getImages() {
        return getCurrentSession().createQuery("from Image").list();
    }

    @Override
    public boolean updateImage(Image image) {
        Image imageToUpdate = getImage(image.getId());
        imageToUpdate.setPathToImage(image.getPathToImage());
        getCurrentSession().update(imageToUpdate);
        Image imageUpdate = getImage(image.getId());
        return (imageUpdate.getPathToImage().equals(image.getPathToImage()));
    }
    
}