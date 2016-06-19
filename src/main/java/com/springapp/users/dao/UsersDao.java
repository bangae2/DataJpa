package com.springapp.users.dao;

import com.springapp.users.entity.AuthEntity;
import com.springapp.users.entity.UsersEntity;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by bangae11 on 2016-06-14.
 */
public interface UsersDao {
    public void usersRegister(UsersEntity usersEntity);
    public void autoAuth(AuthEntity authEntity);
    public UsersEntity loadUserByUsername(String username);
    public void resetPassword(String email, String password);
}
