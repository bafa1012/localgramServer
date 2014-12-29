package com.hska.localgram.dao;

import com.hska.localgram.model.Image;
import com.hska.localgram.model.Tag;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Fabian BÃ¤uerlein <bafa1012@hs-karlsruhe.de>
 */
@Repository
public class TagDAOImpl implements ITagDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private IImageDAO imageDAO;
    

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
    public Tag addTag(Tag newTag, Image newImage) {
        Tag tag = newTag;
        Image image = imageDAO.getImageByFileNameAndUser(newImage.getFile_name(), newImage.getOwner());
        if (image == null) {
            image = newImage;
        }
        if ((tag.getId() == null) ||
                ((image.getId() != null)
                && (tag.getImageById(image.getId()) != null)
                && (tag.getImageById(image.getId()) == null))) {
            getCurrentSession().merge(tag);
            getCurrentSession().save(tag);
        }
//        return em.merge(tag);
        return getTagByContent(tag.getTag());
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
    public Tag getTagByContent(String content) {
        List<Tag> tags = getTags();
        for (Tag tag : tags) {
            if (tag.getTag().equals(content)) {
                return tag;
            }
        }
        return null;
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
