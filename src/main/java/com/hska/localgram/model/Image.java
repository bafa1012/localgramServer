package com.hska.localgram.model;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Entity
@Table(name = "image")
@Transactional
public class Image implements Serializable {

    private static final long serialVersionUID = -5824467694234149525L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id", nullable = false)
    private Long image_id;
    private double latitude;
    private double longitude;
    private String file_name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private AppUser owner;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "image_tag_join",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tag_list;

    /**
     * @return the file_name
     */
    public String getFile_name() {
        return file_name;
    }

    /**
     * @param file_name the file_name to set
     * @return 
     */
    public Image setFile_name(String file_name) {
        this.file_name = file_name;
        return this;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     * @return 
     */
    public Image setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the geo_location_y to set
     * @return 
     */
    public Image setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * @return the image_id
     */
    public Long getId() {
        return image_id;
    }

    /**
     * @param id the image_id to set
     */
    public Image setId(Long id) {
        this.image_id = id;
        return this;
    }

    /**
     * @return the owner
     */
    public AppUser getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public Image setOwner(AppUser owner) {
        this.owner = owner;
        return this;
    }

    /**
     * @return the tag_list
     */
    public Set<Tag> getTag_list() {
        if (tag_list == null) {
            return new HashSet<>();
        }
        return new HashSet<>(tag_list);
    }

    /**
     * @param tags
     * @return 
     */
    public Image addTagSet(Set<Tag> tags) {
        if (this.tag_list == null) {
            tag_list = new HashSet<>();
        }
        for (Tag tag : tags) {
            this.tag_list.add(tag);
        }
        return this;
    }

    /**
     * @param tag
     * @return 
     */
    public Image addTag(Tag tag) {
        if (this.tag_list == null) {
            tag_list = new HashSet<>();
        }
        this.tag_list.add(tag);
        return this;
    }
}
