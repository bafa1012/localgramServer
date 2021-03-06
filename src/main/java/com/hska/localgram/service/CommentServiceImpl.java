package com.hska.localgram.service;

import com.hska.localgram.dao.ICommentDAO;
import com.hska.localgram.model.Comment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Service
@Transactional
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentDAO commentDAO;

    @Override
    public void addComment(Comment comment) {
        commentDAO.addComment(comment);
    }

    @Override
    public boolean deleteComment(Long id) {
        return commentDAO.deleteComment(id);
    }

    @Override
    public Comment getComment(Long id) {
        return commentDAO.getComment(id);
    }

    @Override
    public Comment getCommentByMessage(String message) {
        return commentDAO.getCommentByMessage(message);
    }

    @Override
    public List<Comment> getComments() {
        return commentDAO.getComments();
    }

    @Override
    public List<Comment> getCommentsByImage(String name) {
        return commentDAO.getCommentsByImage(name);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentDAO.updateComment(comment);
    }
}
