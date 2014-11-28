/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hska.localgram.dao;

import com.hska.localgram.model.Tag;
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
public class TagDAOImpl implements ITagDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean addTag(Tag tag) {
        getCurrentSession().save(tag);
        return true;
    }

    @Override
    public boolean deleteTag(Long id) {
        Tag tag = getTag(id);
        if (tag != null) {
            getCurrentSession().delete(tag);
        }
        return getTag(id) == null;
    }

    @Override
    public Tag getTag(Long id) {
        return (Tag) getCurrentSession().get(Tag.class, id);
    }

    @Override
    public List<Tag> getTags() {
        return getCurrentSession().createQuery("from Tag").list();
    }

    @Override
    public boolean updateTag(Tag tag) {
        Tag tagToUpdate = getTag(tag.getId());
        tagToUpdate.setTag(tag.getTag());
        getCurrentSession().update(tagToUpdate);
        Tag tagUpdate = getTag(tag.getId());
        return (tagUpdate.getTag().equals(tag.getTag()));
    }
    
}
