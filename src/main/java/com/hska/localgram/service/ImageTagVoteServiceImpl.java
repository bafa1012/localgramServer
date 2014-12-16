package com.hska.localgram.service;

import com.hska.localgram.dao.IImageTagVoteDAO;
import com.hska.localgram.model.Image;
import com.hska.localgram.model.ImageTagVote;
import com.hska.localgram.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Service
@Transactional
public class ImageTagVoteServiceImpl implements IImageTagVoteService {

    @Autowired
    private IImageTagVoteDAO voteDAO;

    @Override
    public ImageTagVote addImageTagVote(Image image, Tag tag) {
        return voteDAO.addImageTagVote(image, tag);
    }

    @Override
    public ImageTagVote getIImageTagVoteDAO(Long id) {
        return voteDAO.getIImageTagVoteDAO(id);
    }

    @Override
    public ImageTagVote getIImageTagVoteDAOByImage(Image image) {
        return voteDAO.getIImageTagVoteDAOByImage(image);
    }

    @Override
    public ImageTagVote getIImageTagVoteDAOByTag(Tag tag) {
        return voteDAO.getIImageTagVoteDAOByTag(tag);
    }

    @Override
    public ImageTagVote vote(Long id) {
        return voteDAO.vote(id);
    }
    
}
