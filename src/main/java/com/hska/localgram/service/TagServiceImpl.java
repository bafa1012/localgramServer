package com.hska.localgram.service;

import com.hska.localgram.dao.ITagDAO;
import com.hska.localgram.model.Image;
import com.hska.localgram.model.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Tag service class.
 *
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Service
@Transactional
public class TagServiceImpl implements ITagService {

    @Autowired
    private ITagDAO tagDAO;

    @Override
    public Tag addTag(Tag tag, Image image) {
        return tagDAO.addTag(tag, image);
    }

    @Override
    public boolean deleteTag(Long id) {
        return tagDAO.deleteTag(id);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDAO.getTag(id);
    }

    @Override
    public List<Tag> getTags() {
        return tagDAO.getTags();
    }

    @Override
    public boolean updateTag(Tag tag) {
        return tagDAO.updateTag(tag);
    }
}
