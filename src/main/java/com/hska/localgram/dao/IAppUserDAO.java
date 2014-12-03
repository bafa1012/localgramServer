package com.hska.localgram.dao;

import com.hska.localgram.model.AppUser;
import java.util.List;

/**
 * @author Fabian Bäuerlein
 */
public interface IAppUserDAO {

    public boolean addAppUser(AppUser appUser);

    public AppUser getAppUser(Long id);

    public AppUser checkLogin(String name, String password);

    AppUser getAppUserByName(String name);

    public boolean updateAppUser(AppUser appUser);

    public boolean deleteAppUser(Long id);

    public List<AppUser> getAppUsers();
}
