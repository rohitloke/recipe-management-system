package com.rms.assignment.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rms.assignment.core.entity.User;

/**
 * JPA repository to manage {@link User} entity in the database.
 * 
 * @author Rohit
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * reads the {@link User} based on it's username property.
     * 
     * @param username of the user to search
     * @return {@link Optional} conatining the user if found, else empty.
     */
    Optional<User> getByUsername(String username);
}
