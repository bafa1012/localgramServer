package com.hska.localgram.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Entity
@Table(name = "image")
public class Image implements Serializable {

    private static final long serialVersionUID = -5824467694234149525L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String pathToImage;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the pathToImage
     */
    public String getPathToImage() {
        return pathToImage;
    }

    /**
     * @param pathToImage the pathToImage to set
     */
    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }
}
