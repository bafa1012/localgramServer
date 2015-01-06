package com.hska.localgram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

/**
 *
 * @author Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Entity
@Table(name = "comment")
@Transactional
public class Comment implements Serializable {
    private static final long serialVersionUID = -2541050196934161215L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id", nullable = false)
    private Long id;
    private String message;
    private String userName;
    private String fileName;
    @OneToOne
    @JsonIgnore
    private AppUser user;
    @OneToOne
    @JsonIgnore
    private Image image;

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param image the image to set
     * @return 
     */
    public Comment setImage(Image image) {
        this.image = image;
        this.fileName = image.getFile_name();
        return this;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     * @return 
     */
    public Comment setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * @return the user
     */
    public AppUser getUser() {
        return user;
    }

    /**
     * @param user the user to set
     * @return 
     */
    public Comment setUser(AppUser user) {
        this.user = user;
        this.userName = user.getName();
        return this;
    }
}
