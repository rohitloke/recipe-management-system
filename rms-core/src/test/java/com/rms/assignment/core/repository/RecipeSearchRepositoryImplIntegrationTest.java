package com.rms.assignment.core.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rms.assignment.AbstractIntegrationTest;
import com.rms.assignment.core.entity.Recipe;
import com.rms.assignment.core.search.SearchQueryData;
import com.rms.assignment.core.search.service.IndexingService;

@ExtendWith(SpringExtension.class)
class RecipeSearchRepositoryImplIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private RecipeSearchRepository recipeSearchRepository;
    @Autowired
    private IndexingService indexingService;

    @BeforeEach
    void setup() {
        indexingService.initiateIndexing();
    }

    @Test
    void testVeg_True() {
        List<Recipe> searchRecipes = recipeSearchRepository
                .searchRecipes(new SearchQueryData(true, 0, null, null, null));
        assertEquals(1, searchRecipes.size());
        assertEquals(OREO_RECIPE_NAME, searchRecipes.get(0).getName());
    }

    @Test
    void testVeg_False() {
        List<Recipe> searchRecipes = recipeSearchRepository
                .searchRecipes(new SearchQueryData(false, 0, null, null, null));
        assertEquals(1, searchRecipes.size());
        assertEquals("other recipe", searchRecipes.get(0).getName());
    }

    @Test
    void testServing_below() {
        List<Recipe> searchRecipes = recipeSearchRepository
                .searchRecipes(new SearchQueryData(null, 9, null, null, null));
        assertEquals(1, searchRecipes.size());
        assertEquals(OREO_RECIPE_NAME, searchRecipes.get(0).getName());
    }

    @Test
    void testServing_above() {
        List<Recipe> searchRecipes = recipeSearchRepository
                .searchRecipes(new SearchQueryData(null, 11, null, null, null));
        assertEquals(0, searchRecipes.size());
    }

    @Test
    void testIngredients_Include_NotFound() {
        List<Recipe> searchRecipes = recipeSearchRepository
                .searchRecipes(new SearchQueryData(null, 0, null, "random", null));
        assertEquals(0, searchRecipes.size());
    }

    @Test
    void testIngredients_Include_Found() {
        List<Recipe> searchRecipes = recipeSearchRepository
                .searchRecipes(new SearchQueryData(null, 0, null, "oreo", null));
        assertEquals(1, searchRecipes.size());
        assertEquals(OREO_RECIPE_NAME, searchRecipes.get(0).getName());
    }

    @Test
    void testIngredients_Exclude_PresentInRecipe() {
        List<Recipe> searchRecipes = recipeSearchRepository
                .searchRecipes(new SearchQueryData(null, 0, null, null, "oreo"));
        assertEquals(1, searchRecipes.size());
        assertEquals(OTHER_RECIPE_NAME, searchRecipes.get(0).getName());
    }

    @Test
    void testIngredients_Exclude_NotPresentInRecipe() {
        List<Recipe> searchRecipes = recipeSearchRepository
                .searchRecipes(new SearchQueryData(null, 0, null, null, "random"));
        assertEquals(2, searchRecipes.size());
        assertThat(searchRecipes.stream().map(Recipe::getName).collect(Collectors.toList()),
                containsInAnyOrder(OREO_RECIPE_NAME, OTHER_RECIPE_NAME));
    }

    @Test
    void testInstructions_NotPresentInRecipe() {
        List<Recipe> searchRecipes = recipeSearchRepository
                .searchRecipes(new SearchQueryData(null, 0, "random", null, null));
        assertEquals(0, searchRecipes.size());
    }

    @Test
    void testInstructions_PresentInRecipe() {
        List<Recipe> searchRecipes = recipeSearchRepository
                .searchRecipes(new SearchQueryData(null, 0, "oreo", null, null));
        assertEquals(1, searchRecipes.size());
        assertEquals(OREO_RECIPE_NAME, searchRecipes.get(0).getName());
    }

    @Test
    void testCombination_All() {
        List<Recipe> searchRecipes = recipeSearchRepository
                .searchRecipes(new SearchQueryData(true, 9, "cream", "oreo", "ingredient"));
        assertEquals(1, searchRecipes.size());
        assertEquals(OREO_RECIPE_NAME, searchRecipes.get(0).getName());
    }

    @Test
    void testCombination_Serves_Ingredient() {
        List<Recipe> searchRecipes = recipeSearchRepository
                .searchRecipes(new SearchQueryData(null, 7, "cookies", null, null));
        assertEquals(1, searchRecipes.size());
        assertEquals(OREO_RECIPE_NAME, searchRecipes.get(0).getName());
    }

    @Test
    void testCombination_ExcludeIngredient_Instruction() {
        List<Recipe> searchRecipes = recipeSearchRepository
                .searchRecipes(new SearchQueryData(null, 0, "preparation", null, "cream"));
        assertEquals(1, searchRecipes.size());
        assertEquals(OTHER_RECIPE_NAME, searchRecipes.get(0).getName());
    }

}
