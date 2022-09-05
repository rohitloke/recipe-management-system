package com.rms.assignment.core.service;

import java.util.List;
import java.util.Optional;

import com.rms.assignment.core.entity.Recipe;

public interface RecipeService {
    /**
     * Gets all recipes from the repository
     * 
     * @return all {@link Recipe}s
     */
    List<Recipe> getAll();

    /**
     * Get {@link Recipe} by Id.
     * 
     * @param id the {@link Recipe} id to look for
     * @return {@link Optional} containing the {@link Recipe} if found, else empty
     *         {@link Optional}.
     */
    Optional<Recipe> getById(long id);

    /**
     * Create a {@link Recipe} in the Datastore
     * 
     * @param recipe to be created
     * @return created {@link Recipe}
     */
    Recipe create(Recipe recipe);

    /**
     * Updates a {@link Recipe} in the Datastore
     * 
     * @param recipe to be created
     * @return updated recipe
     */
    Recipe update(Recipe recipe);

    /**
     * Search the recipes based on the given parameters. If all parameters are null
     * then returns all {@link Recipe}s without using recipe indexes.
     * 
     * @param instructions       recipe instructions
     * @param includeIngredients recipe ingredients to be present in recipes found
     * @param excludeIngredients recipe ingredients to be not present in recipes
     *                           found
     * @param servings           servings size
     * @param onlyVeg            veg or nonveg recipes. Both if the parameter is
     *                           <code>null</code>
     * @return {@link Recipe}s found
     */
    List<Recipe> searchRecipes(String instructions, String includeIngredients, String excludeIngredients,
            Integer servings, Boolean onlyVeg);
}
