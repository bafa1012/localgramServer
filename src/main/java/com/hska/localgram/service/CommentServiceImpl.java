package com.hska.localgram.service;

import com.hska.localgram.dao.ICommentDAO;
import com.hska.localgram.model.AppUser;
import com.hska.localgram.model.Image;
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
    public Comment addComment(String message, Image image, AppUser user) {
        return commentDAO.addComment(message, image, user);
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
    public Comment updateComment(Comment comment) {
        return commentDAO.updateComment(comment);
    }
}
