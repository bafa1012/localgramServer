/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hska.localgram.dao;

import com.hska.localgram.model.Image;
import java.util.List;

/**
 *
 * @author F
 */
public interface IImageDAO {

    public boolean addImage(Image image);
    public Image getImage(Long id);
    public boolean updateImage(Image image);
    public boolean deleteImage(Long id);
    public List<Image> getImages();
}
