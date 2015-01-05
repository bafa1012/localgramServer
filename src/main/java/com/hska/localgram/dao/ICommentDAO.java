package com.hska.localgram.dao;

import com.hska.localgram.model.AppUser;
import com.hska.localgram.model.Image;
import com.hska.localgram.model.Comment;
import java.util.List;

/**
 *
 * @author Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
public interface ICommentDAO {

    public Comment addComment(Comment comment);

    public Comment getComment(Long id);

    public Comment getCommentByMessage(String message);

    public Comment updateComment(Comment comment);

    public boolean deleteComment(Long id);

    public List<Comment> getComments();
}
