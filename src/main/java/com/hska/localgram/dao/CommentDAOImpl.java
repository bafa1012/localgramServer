package com.hska.localgram.dao;

import com.hska.localgram.model.Comment;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
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

    @Override
    public List<Comment> getCommentsByImage(String name) {
        List<Comment> comments = getComments();
        Iterator<Comment> it = comments.iterator();
        while (it.hasNext()) {
            // Split the file name at dot of file ending
            String fileName = it.next().getImage().getFile_name().split("\\.")[0];
            if (!fileName.equals(name)) {
                it.remove();
            }
        }
        return comments;
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
    public void addComment(Comment comment) {
        getCurrentSession().save(comment);
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
        SQLQuery query = getCurrentSession()
                .createSQLQuery("select * from Comment where message = :message");
        query.setParameter("message", message);
        query.addEntity(Comment.class);
        List<Comment> list = query.list();
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
