package com.rms.assignment.core.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Represents the {@link User} entity.
 * 
 * @author Rohit
 *
 */
@Entity
@Table(name = User.TABLE_NAME_USER)
public class User extends BaseEntity {
    private static final long serialVersionUID = -4651502469176756367L;

    public static final String TABLE_NAME_USER = "users";
    public static final String TABLE_NAME_FAV_RECIPES = "fav_recipes";

    @Column(name = "username")
    private String username; // the user name
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "fav_recipes", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"))
    private List<Recipe> favouriteRecipes; // the user's favorite recipes. One recipe can be favorited by many user and
                                           // one user has many recipes.

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(List<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }

}
