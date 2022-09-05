package com.rms.assignment.core.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.assignment.core.entity.Recipe;
import com.rms.assignment.core.entity.User;
import com.rms.assignment.core.repository.RecipeRepository;
import com.rms.assignment.core.repository.UserRepository;
import com.rms.assignment.core.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public User create(User user) {
        // USers don't create recipes they only favourite existing recipe
        List<Recipe> recipes = user.getFavouriteRecipes().stream()
                .map(r -> recipeRepository.getByName(r.getName()).orElse(null)).filter(Objects::nonNull)
                .collect(Collectors.toList());
        user.setFavouriteRecipes(recipes);
        return userRepository.save(user);
    }
}
