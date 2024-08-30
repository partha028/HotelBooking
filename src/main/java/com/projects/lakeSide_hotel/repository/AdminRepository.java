package com.projects.lakeSide_hotel.repository;

import com.projects.lakeSide_hotel.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<UserDetails, Long> {

    @Query("SELECT u FROM UserDetails u WHERE u.userName = :username")
    Optional<String> findUserName(@Param("username") String userName);

    @Query("SELECT u FROM UserDetails u WHERE u.userName = :username AND u.password = :password")
    Optional<UserDetails> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("SELECT u FROM UserDetails u ORDER BY u.id ASC LIMIT 1")
    Optional<UserDetails> getUserDetails();

    @Query("SELECT u FROM UserDetails u WHERE u.userName = :username AND u.securityQuestion = :securityQuestion AND u.securityAnswer =:securityAnswer")
    Optional<UserDetails> resetPassword(@Param("username") String username, @Param("securityQuestion") String securityQuestion, @Param("securityAnswer") String securityAnswer);

    @Transactional
    @Modifying
    @Query("UPDATE UserDetails u SET u.password = :password WHERE u.userName = :username")
    void updatePassword(@Param("username") String userName, @Param("password") String password);

}
