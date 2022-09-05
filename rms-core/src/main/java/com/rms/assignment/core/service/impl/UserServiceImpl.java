package com.rms.assignment.core.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.assignment.core.entity.User;
import com.rms.assignment.core.repository.UserRepository;
import com.rms.assignment.core.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
}
