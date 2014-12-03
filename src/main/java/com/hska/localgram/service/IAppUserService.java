package com.hska.localgram.service;

import com.hska.localgram.model.AppUser;
import java.util.List;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
public interface IAppUserService {

    public boolean addAppUser(AppUser appUser);

    public AppUser getAppUser(Long id);

    public AppUser checkLogin(String name, String password);

    public AppUser getAppUserByName(String name);

    public boolean updateAppUser(AppUser appUser);

    public boolean deleteAppUser(Long id);

    public List<AppUser> getAppUsers();
}
