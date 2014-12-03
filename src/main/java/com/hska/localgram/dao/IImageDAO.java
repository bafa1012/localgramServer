package com.hska.localgram.dao;

import com.hska.localgram.model.Image;
import java.util.List;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
public interface IImageDAO {

    public boolean addImage(Image image);

    public Image getImage(Long id);

    public boolean updateImage(Image image);

    public boolean deleteImage(Long id);

    public List<Image> getImages();
}
