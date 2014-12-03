package com.hska.localgram.service;

import com.hska.localgram.model.Tag;
import java.util.List;

/**
 * @author Fabian Bäuerlein
 */
public interface ITagService {

    public boolean addTag(Tag tag);
    public Tag getTag(Long id);
    public boolean updateTag(Tag tag);
    public boolean deleteTag(Long id);
    public List<Tag> getTags();
}
