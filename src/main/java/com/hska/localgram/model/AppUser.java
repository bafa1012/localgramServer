package com.hska.localgram.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Entity
@Table(name = "appuser")
public class AppUser implements Serializable {

    private static final long serialVersionUID = -5486959642628288462L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String mail;
    @JsonIgnore
    private String password;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     * @return 
     */
    public AppUser setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     * @return
     */
    public AppUser setMail(String mail) {
        this.mail = mail;
        return this;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     * @return
     */
    public AppUser setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return the password
     */
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     * @return
     */
    public AppUser setPassword(String password) {
        this.password = password;
        return this;
    }
}
