package com.hska.localgram.service;

import com.hska.localgram.model.Image;
import com.hska.localgram.model.ImageTagVote;
import com.hska.localgram.model.Tag;

/**
 *
 * @author Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
public interface IImageTagVoteService {
    
    public ImageTagVote addImageTagVote(Image image, Tag tag);

    public ImageTagVote getIImageTagVoteDAO(Long id);

    public ImageTagVote vote(Long id);

    public ImageTagVote getIImageTagVoteDAOByImage(Image image);

    public ImageTagVote getIImageTagVoteDAOByTag(Tag tag);
}
