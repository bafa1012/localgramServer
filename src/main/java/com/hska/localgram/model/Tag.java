package com.hska.localgram.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Entity
@Table(name = "tag")
@Transactional
public class Tag implements Serializable {

    private static final long serialVersionUID = -4518791216791526974L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id", nullable = false)
    private Long id;
    private String tag;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "image_tag_join",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private Set<Image> images;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    
    public Image getImageById(Long id) {
        List<Image> images = new ArrayList<>(getImages());
        for (Image image : images) {
            if (image.getId().equals(id)) {
                return image;
            }
        }
        return null;
    }

    /**
     * @return the images
     */
    public Set<Image> getImages() {
        if (images == null) {
            return new HashSet<>();
        }
        return new HashSet<>(images);
    }

    /**
     * @param id the id to set
     * @return 
     */
    public Tag setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * @param image the images to set
     * @return 
     */
    public Tag addImage(Image image) {
        if (this.images == null) {
            images = new HashSet<>();
        }
        this.images.add(image);
        return this;
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     * @return 
     */
    public Tag setTag(String tag) {
        this.tag = tag;
        return this;
    }
}
