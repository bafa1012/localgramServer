/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hska.localgram.service;

import com.hska.localgram.model.AppUser;
import com.hska.localgram.dao.AppUserDAOImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author F
 */
@Service
@Transactional
public class AppUserServiceImpl implements IAppUserService {

    @Autowired
    private AppUserDAOImpl appUserDAO;

    @Override
    public boolean addAppUser(AppUser appUser) {
        return appUserDAO.addAppUser(appUser);
    }

    @Override
    public boolean deleteAppUser(Long id) {
        return appUserDAO.deleteAppUser(id);
    }

    @Override
    public AppUser getAppUser(Long id) {
        return appUserDAO.getAppUser(id);
    }
    
    @Override
    public AppUser getAppUserByAuthentification(String auth) {
        return appUserDAO.getAppUserByAuthentification(auth);
    }
    
    @Override
    public AppUser getAppUserByName(String name) {
        return appUserDAO.getAppUserByName(name);
    }

    @Override
    public AppUser checkLogin(String name, String password) {
        return appUserDAO.checkLogin(name, password);
    }

    @Override
    public List<AppUser> getAppUsers() {
        return appUserDAO.getAppUsers();
    }

    @Override
    public boolean updateAppUser(AppUser appUser) {
        return appUserDAO.updateAppUser(appUser);
    }

}
