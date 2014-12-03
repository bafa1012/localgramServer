/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hska.localgram.dao;

import com.hska.localgram.model.AppUser;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author F
 */
@Repository
public class AppUserDAOImpl implements IAppUserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean addAppUser(AppUser appUser) {
        getCurrentSession()
                .save(appUser);
        return true;
    }

    @Override
    public boolean deleteAppUser(Long id) {
        AppUser appUser = getAppUser(id);
        if (appUser != null) {
            getCurrentSession()
                    .delete(appUser);
        }
        return getAppUser(id) == null;
    }

    @Override
    public AppUser getAppUser(Long id) {
        return (AppUser) getCurrentSession()
                .get(AppUser.class, id);
    }

    @Override
    public AppUser getAppUserByName(String name) {
        List<AppUser> list = getAppUsers();
        for (AppUser user : list) {
            if (user.getName()
                    .equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public AppUser getAppUserByAuthentification(String auth) {
        List<AppUser> list = getAppUsers();
        for (AppUser user : list) {
            Encoder encoder = Base64.getEncoder();
            auth = encoder.encodeToString(auth.getBytes());
            if (auth.equals(user.getName() + ":" + user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public AppUser checkLogin(String name, String password) {
        List<AppUser> users = getAppUsers();
        for (AppUser user : users) {
            if (user.getName()
                    .equals(name) && user.getPassword()
                    .equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<AppUser> getAppUsers() {
        return getCurrentSession()
                .createQuery("from AppUser")
                .list();
    }

    @Override
    public boolean updateAppUser(AppUser appUser) {
        AppUser appUserToUpdate = getAppUser(appUser.getId());
        appUserToUpdate.setMail(appUser.getMail());
        appUserToUpdate.setName(appUser.getName());
        appUserToUpdate.setPassword(appUser.getPassword());
        getCurrentSession()
                .update(appUserToUpdate);
        AppUser user = getAppUser(appUser.getId());
        return user.getMail()
                .equals(appUser.getMail())
                && user.getName()
                .equals(user.getName())
                && user.getPassword()
                .equals(user.getPassword());
    }
}