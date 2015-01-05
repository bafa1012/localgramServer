package com.hska.localgram.model;

/**
 *
 * @author Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
public class ImageContainer {
    private String description;
    private String[][] meta;
    private String[][] tags;
    private byte[][] bitmaps;
    
    /**
     * @param description
     * @param meta array of meta data -> first index indicates to the images,
     *                                   second is build as follows:
     *                                   0 -> file name
     *                                   1 -> latitude
     *                                   2 -> longitude
     *                                   3 -> user id
     * @param tags array with associated tags
     * @param bitmaps array of bytes
     */
    public ImageContainer(String description, String[][] meta, String[][] tags, byte[][] bitmaps) {
        this.description = description;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
