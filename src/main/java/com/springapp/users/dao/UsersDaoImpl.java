package com.springapp.users.dao;

import com.springapp.users.entity.AuthEntity;
import com.springapp.users.entity.UsersEntity;
import com.springapp.users.repository.AuthRepository;
import com.springapp.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 * Created by bangae11 on 2016-06-14.
 */
@Repository
public class UsersDaoImpl implements UsersDao {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthRepository authRepository;

    @Override
    public void usersRegister(UsersEntity usersEntity) {
        usersRepository.save(usersEntity);
    }

    @Override
    public void autoAuth(AuthEntity authEntity) {
        authRepository.save(authEntity);
    }

    @Override
    public UsersEntity loadUserByUsername(String username) {
        return this.usersRepository.findOne(username);
    }

    @Override
    public void resetPassword(String email, String password) {
        this.usersRepository.resetPassword(email, password);
    }
}
