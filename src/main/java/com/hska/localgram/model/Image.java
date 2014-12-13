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
    private int geo_location_x;
    private int geo_location_y;
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
     */
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    /**
     * @return the geo_location_x
     */
    public int getGeo_location_x() {
        return geo_location_x;
    }

    /**
     * @param geo_location_x the geo_location_x to set
     */
    public void setGeo_location_x(int geo_location_x) {
        this.geo_location_x = geo_location_x;
    }

    /**
     * @return the geo_location_y
     */
    public int getGeo_location_y() {
        return geo_location_y;
    }

    /**
     * @param geo_location_y the geo_location_y to set
     */
    public void setGeo_location_y(int geo_location_y) {
        this.geo_location_y = geo_location_y;
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
    public void setId(Long id) {
        this.image_id = id;
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
    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    /**
     * @return the tag_list
     */
    public Set<Tag> getTag_list() {
        return tag_list;
    }

    /**
     * @param tag_list the tag_list to set
     */
    public void setTag_list(Set<Tag> tag_list) {
        this.tag_list = tag_list;
    }
}
