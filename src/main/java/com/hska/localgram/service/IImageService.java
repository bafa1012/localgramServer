package com.hska.localgram.service;

import com.hska.localgram.model.Image;
import java.util.List;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
public interface IImageService {

    public Image addImage(Image image);

    public Image getImage(Long id);

    public List<Image> getImagesByGeoLocation(double latitude, double longitude, int radius);

    public List<Image> getImagesByTag(Long tagID);

    public boolean updateImage(Image image);

    public boolean deleteImage(Long id);

    public List<Image> getImages();

    public List<Image> getImagesByUser(Long Owner);
}
