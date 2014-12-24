package com.hska.localgram.model;

/**
 *
 * @author Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
public class ImageContainer {
    private String[][] meta;
    private String[][] tags;
    private byte[][] bitmaps;
    
    /**
     * @param meta array of meta data -> first index indicates to the images,
     *                                   second is build as follows:
     *                                   0 -> file name
     *                                   1 -> latitude
     *                                   2 -> longitude
     *                                   3 -> user id
     * @param tags array with associated tags
     * @param bitmaps array of bytes
     */
    public ImageContainer(String[][] meta, String[][] tags, byte[][] bitmaps) {
        this.meta = meta;
        this.tags = tags;
        this.bitmaps = bitmaps;
    }
    
    public ImageContainer() {
        
    }

    /**
     * @return the bitmaps
     */
    public byte[][] getBitmaps() {
        return bitmaps;
    }

    /**
     * @return the meta
     */
    public String[][] getMeta() {
        return meta;
    }

    /**
     * @return the tags
     */
    public String[][] getTags() {
        return tags;
    }
}
