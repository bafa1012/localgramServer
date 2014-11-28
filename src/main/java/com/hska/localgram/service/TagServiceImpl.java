/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hska.localgram.service;

import com.hska.localgram.dao.TagDAOImpl;
import com.hska.localgram.model.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author F
 */
@Service
@Transactional
public class TagServiceImpl implements ITagService {

    @Autowired
    private TagDAOImpl tagDAO;

    @Override
    public boolean addTag(Tag tag) {
        return tagDAO.addTag(tag);
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
