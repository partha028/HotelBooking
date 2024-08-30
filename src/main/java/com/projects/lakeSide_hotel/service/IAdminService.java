package com.projects.lakeSide_hotel.service;

import com.projects.lakeSide_hotel.model.UserDetails;

import java.util.Optional;

public interface IAdminService {
    boolean validateUserLogin(UserDetails userDetails);

    boolean saveUser(UserDetails userDetails);

    Optional<UserDetails> getUserDetails();

    String resetPassword(UserDetails userDetails);
}
