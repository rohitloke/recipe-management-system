package com.rms.assignment.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * Represents the {@link Recipe}
 * 
 * @author Rohit
 */
@Entity
@Table(name = Recipe.TABLE_NAME_RECIPE)
@Indexed(index = "recipes_idx")
public class Recipe extends BaseEntity {
    public static final String INGREDIENTS_INDEX_FIELD_NAME = "ingredients";
    public static final String INSTRUCTIONS_INDEX_FIELD_NAME = "instructions";
    public static final String SERVES_INDEX_FIELD_NAME = "serves";
    public static final String VEG_INDEX_FIELD_NAME = "veg";

    private static final long serialVersionUID = 1724547054061648922L;

    public static final String TABLE_NAME_RECIPE = "recipes";

    @Column(name = "name")
    private String name; // recipe name
    @Column(name = "description")
    private String description; // recipe description
    @Column(name = VEG_INDEX_FIELD_NAME)
    @Field(name = VEG_INDEX_FIELD_NAME)
    private Boolean veg; // whether veg of non veg recipe
    @Column(name = SERVES_INDEX_FIELD_NAME)
    @Field(name = SERVES_INDEX_FIELD_NAME)
    private int serves;
    @Column(name = INSTRUCTIONS_INDEX_FIELD_NAME)
    @Field(name = INSTRUCTIONS_INDEX_FIELD_NAME)
    private String instructions; // instructions for preparation
    @Column(name = INGREDIENTS_INDEX_FIELD_NAME)
    @Field
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

    public int getServes() {
        return serves;
    }

    public void setServes(int serves) {
        this.serves = serves;
    }

}
