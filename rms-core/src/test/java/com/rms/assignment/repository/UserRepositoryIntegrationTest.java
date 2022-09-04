package com.rms.assignment.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rms.assignment.AbstractIntegrationTest;
import com.rms.assignment.core.entity.Recipe;
import com.rms.assignment.core.entity.User;
import com.rms.assignment.core.repository.UserRepository;

@ExtendWith(SpringExtension.class)
class UserRepositoryIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testGetByUsername() {
        String username = "souschef";
        Optional<User> result = userRepository.getByUsername(username);
        assertTrue(result.isPresent());
        User user = result.get();
        assertEquals(username, user.getUsername());
        List<Recipe> favouriteRecipes = user.getFavouriteRecipes();
        assertEquals(1, favouriteRecipes.size());
        assertEquals("Oreo Ice Cream Dessert", favouriteRecipes.get(0).getName());
    }

}
