package com.rms.assignment.core.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.assignment.core.entity.Recipe;
import com.rms.assignment.core.repository.RecipeRepository;
import com.rms.assignment.core.search.SearchQueryData;
import com.rms.assignment.core.search.service.RecipeSearchService;
import com.rms.assignment.core.service.RecipeService;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeSearchService recipeSearchService;

    @Override
    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    @Override
    public Optional<Recipe> getById(long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public Recipe create(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe update(Recipe recipe) {
        recipeRepository.getByName(recipe.getName()).ifPresentOrElse(r -> copyValues(r, recipe), () -> {
            throw new EntityNotFoundException("Recipe with the name " + recipe.getName() + " not found");
        });
        return recipe;
    }

    @Override
    public List<Recipe> searchRecipes(String instructions, String includeIngredients, String excludeIngredients,
            Integer servings, Boolean onlyVeg) {
        if (instructions == null && includeIngredients == null && excludeIngredients == null && onlyVeg == null
                && servings == null) {
            return getAll(); // if not search parameters given return all recipes without hitting search
                             // indices.
        }
        return recipeSearchService.searchRecipes(new SearchQueryData(onlyVeg, servings.intValue(), instructions,
                includeIngredients, excludeIngredients));
    }

    private void copyValues(Recipe existing, Recipe newRecipe) {
        existing.setDescription(newRecipe.getDescription());
        existing.setVeg(newRecipe.getVeg());
        existing.setInstructions(newRecipe.getInstructions());
        existing.setIngredients(newRecipe.getIngredients());
    }

}
