package com.hska.localgram.dao;

import com.hska.localgram.model.AppUser;
import com.hska.localgram.model.Image;
import com.hska.localgram.model.Comment;
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
public class CommentDAOImpl implements ICommentDAO {
    
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
    public Comment addComment(String message, Image image, AppUser user) {
        Comment comment = new Comment().setMessage(message).setImage(image).setUser(user);
        getCurrentSession().save(comment);
        return getCommentByMessage(comment.getMessage());
    }

    @Override
    public boolean deleteComment(Long id) {
        Comment comment = getComment(id);
        if (comment != null) {
            getCurrentSession().delete(comment);
        }
        return getComment(id) == null;
    }

    @Override
    public Comment getComment(Long id) {
        return (Comment) getCurrentSession().get(Comment.class, id);
    }

    @Override
    public Comment getCommentByMessage(String message) {
        List<Comment> list = getCurrentSession()
                .createQuery("from Comment where message = " + message)
                .list();
        return list.get(0);
    }

    @Override
    public List<Comment> getComments() {
        return getCurrentSession().createQuery("from Comment").list();
    }

    @Override
    public Comment updateComment(Comment comment) {
        Comment commentToUpdate = getComment(comment.getId());
        commentToUpdate.setMessage(comment.getMessage());
        commentToUpdate.setImage(comment.getImage());
        commentToUpdate.setUser(comment.getUser());
        getCurrentSession().update(commentToUpdate);
        return getComment(comment.getId());
    }
}
