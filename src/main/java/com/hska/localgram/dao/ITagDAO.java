/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hska.localgram.dao;

import com.hska.localgram.model.Tag;
import java.util.List;

/**
 *
 * @author F
 */
public interface ITagDAO {

    public boolean addTag(Tag tag);
    public Tag getTag(Long id);
    public boolean updateTag(Tag tag);
    public boolean deleteTag(Long id);
    public List<Tag> getTags();
}
