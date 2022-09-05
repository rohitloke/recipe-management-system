package com.rms.assignment.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RecipeDto {

    private final String name;
    private final String description;
    private final Boolean veg;
    private final int serves;
    private final String instructions;
    private final String ingredients;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RecipeDto(@JsonProperty("name") String name, @JsonProperty("description") String description,
            @JsonProperty("veg") Boolean veg, @JsonProperty("serves") int serves,
            @JsonProperty("instructions") String instructions, @JsonProperty("ingredients") String ingredients) {
        this.name = name;
        this.description = description;
        this.veg = veg;
        this.serves = serves;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getVeg() {
        return veg;
    }

    public int getServes() {
        return serves;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

}
