package com.rms.assignment.api.dto;

import java.util.List;

public class UserDto {

    private final String username;
    private final List<RecipeDto> favouriteRecipes;

    public UserDto(String username, List<RecipeDto> favouriteRecipes) {
        this.username = username;
        this.favouriteRecipes = favouriteRecipes;
    }

    public String getUsername() {
        return username;
    }

    public List<RecipeDto> getFavouriteRecipes() {
        return favouriteRecipes;
    }

}
