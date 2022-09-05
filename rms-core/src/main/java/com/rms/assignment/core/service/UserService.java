package com.rms.assignment.core.service;

import java.util.Optional;

import com.rms.assignment.core.entity.User;

public interface UserService {
    /**
     * reads the {@link User} based on it's username property.
     * 
     * @param username of the user to search
     * @return {@link Optional} containing the user if found, else empty.
     */
    Optional<User> getByUsername(String username);

    /**
     * handles creation of the user
     * 
     * @param user {@link User} to be created
     * @return create {@link User}
     */
    User create(User user);

}
