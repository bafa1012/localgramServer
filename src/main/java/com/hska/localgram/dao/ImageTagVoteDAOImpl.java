package com.hska.localgram.dao;

import com.hska.localgram.model.Image;
import com.hska.localgram.model.ImageTagVote;
import com.hska.localgram.model.Tag;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Repository
public class ImageTagVoteDAOImpl implements IImageTagVoteDAO {
    
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
    public ImageTagVote addImageTagVote(Image image, Tag tag) {
        ImageTagVote vote = new ImageTagVote();
        vote.setImage(image);
        vote.setTag(tag);
        vote.addVote();
        getCurrentSession().save(vote);
        return vote;
    }

    @Override
    public ImageTagVote getIImageTagVoteDAO(Long id) {
        return (ImageTagVote) getCurrentSession().get(ImageTagVote.class, id);
    }

    @Override
    public ImageTagVote getIImageTagVoteDAOByImage(Image image) {
        List<ImageTagVote> votes = getCurrentSession().createQuery("from ImageTagVote").list();
        for (ImageTagVote vote : votes) {
            if (vote.getImage().equals(image)) {
                return vote;
            }
        }
        return null;
    }

    @Override
    public ImageTagVote getIImageTagVoteDAOByTag(Tag tag) {
        List<ImageTagVote> votes = getCurrentSession().createQuery("from ImageTagVote").list();
        for (ImageTagVote vote : votes) {
            if (vote.getTag().equals(tag)) {
                return vote;
            }
        }
        return null;
    }

    @Override
    public ImageTagVote vote(Long id) {
        ImageTagVote vote = getIImageTagVoteDAO(id);
        vote.addVote();
        getCurrentSession().save(vote);
        return vote;
    }
    
}
