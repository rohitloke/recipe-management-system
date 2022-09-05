package com.rms.assignment.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

    private final String username;
    private final List<RecipeDto> favouriteRecipes = new ArrayList<>();

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserDto(@JsonProperty("username") String username,
            @JsonProperty("favouriteRecipes") List<RecipeDto> favouriteRecipes) {
        this.username = username;
        this.favouriteRecipes.addAll(favouriteRecipes);
    }

    public String getUsername() {
        return username;
    }

    public List<RecipeDto> getFavouriteRecipes() {
        return favouriteRecipes;
    }

}
