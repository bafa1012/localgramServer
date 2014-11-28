/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hska.localgram.dao;

import com.hska.localgram.model.AppUser;
import java.util.List;

/**
 *
 * @author F
 */
public interface IAppUserDAO {

    public boolean addAppUser(AppUser appUser);
    public AppUser getAppUser(Long id);
    public AppUser checkLogin(String name, String password);

    AppUser getAppUserByName(String name);

    AppUser getAppUserByAuthentification(String name);
    public boolean updateAppUser(AppUser appUser);
    public boolean deleteAppUser(Long id);
    public List<AppUser> getAppUsers();
}
