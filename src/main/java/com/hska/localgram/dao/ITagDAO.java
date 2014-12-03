package com.hska.localgram.dao;

import com.hska.localgram.model.Tag;
import java.util.List;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
public interface ITagDAO {

    public boolean addTag(Tag tag);

    public Tag getTag(Long id);

    public boolean updateTag(Tag tag);

    public boolean deleteTag(Long id);

    public List<Tag> getTags();
}
