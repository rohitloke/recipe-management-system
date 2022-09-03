package com.rms.assignment.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents the {@link Recipe}
 * 
 * @author Rohit
 */
@Entity
@Table(name = Recipe.TABLE_NAME_RECIPE)
public class Recipe extends BaseEntity {
    private static final long serialVersionUID = 1724547054061648922L;

    public static final String TABLE_NAME_RECIPE = "recipes";

    @Column(name = "name")
    private String name; // recipe name
    @Column(name = "description")
    private String description; // recipe description
    @Column(name = "veg")
    private Boolean veg; // whether veg of non veg recipe
    @Column(name = "serves")
    private int serves;
    @Column(name = "instructions")
    private String instructions; // instructions for preparation
    @Column(name = "ingredients")
    private String ingredients; // ingredients needed for the recipe.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getVeg() {
        return veg;
    }

    public void setVeg(Boolean veg) {
        this.veg = veg;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

}
