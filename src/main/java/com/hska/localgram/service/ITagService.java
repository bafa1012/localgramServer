package com.hska.localgram.service;

import com.hska.localgram.model.Image;
import com.hska.localgram.model.Tag;
import java.util.List;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
public interface ITagService {

    public Tag addTag(Tag tag, Image image);

    public Tag getTag(Long id);

    public boolean updateTag(Tag tag);

    public boolean deleteTag(Long id);

    public List<Tag> getTags();
}
