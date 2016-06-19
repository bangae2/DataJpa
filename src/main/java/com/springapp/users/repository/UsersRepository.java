package com.springapp.users.repository;

import com.springapp.users.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bangae1 on 2016-06-13.
 */
public interface UsersRepository extends JpaRepository<UsersEntity, String> {
    @Query(value = "select * from users inner join auth on users.id = auth.id and users.id = :id", nativeQuery = true)
    List<UsersEntity> usersFindAll(@Param("id") String id);

    @Modifying
    @Query(value="update users set password = :password where email = :email", nativeQuery = true)
    int resetPassword(@Param("email")String email, @Param("password")String password);
}
