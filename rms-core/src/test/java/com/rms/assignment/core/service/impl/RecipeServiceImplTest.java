package com.rms.assignment.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rms.assignment.core.entity.Recipe;
import com.rms.assignment.core.repository.RecipeRepository;
import com.rms.assignment.core.search.SearchQueryData;
import com.rms.assignment.core.search.service.RecipeSearchService;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private RecipeSearchService recipeSearchService;

    @InjectMocks
    private RecipeServiceImpl sut;

    @Captor
    private ArgumentCaptor<SearchQueryData> searchArgumentCaptor;

    @Test
    public void testGetAll() {
        List<Recipe> recipeList = Collections.singletonList(new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipeList);

        assertEquals(recipeList, sut.getAll());
    }

    @Test
    public void testFindById() {
        Recipe recipe = new Recipe();
        when(recipeRepository.findById(100L)).thenReturn(Optional.of(recipe));

        assertEquals(recipe, sut.getById(100L).get());
    }

    @Test
    public void testCreate() {
        Recipe recipe = new Recipe();
        sut.create(recipe);

        verify(recipeRepository).save(recipe);
    }

    @Test
    public void testUpdate() {
        Recipe recipe = new Recipe();
        recipe.setDescription("desc");
        recipe.setIngredients("ingredients");
        recipe.setInstructions("instruction");
        recipe.setVeg(true);
        recipe.setName("test");

        when(recipeRepository.getByName("test")).thenReturn(Optional.of(new Recipe()));

        Recipe result = sut.update(recipe);

        assertEquals("desc", result.getDescription());
        assertEquals("ingredients", result.getIngredients());
        assertEquals("instruction", result.getInstructions());
        assertTrue(result.getVeg());
    }

    @Test
    void testUpdate_exception() {
        Recipe recipe = new Recipe();
        recipe.setName("test");
        when(recipeRepository.getByName("test")).thenReturn(Optional.empty());
        EntityNotFoundException assertThrows = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            sut.update(recipe);
        });
        Assertions.assertEquals("Recipe with the name test not found", assertThrows.getMessage());
    }

    @Test
    public void testSearchRecipes() {
        List<Recipe> recipeList = Collections.singletonList(new Recipe());
        when(recipeSearchService.searchRecipes(any())).thenReturn(recipeList);
        assertEquals(recipeList, sut.searchRecipes("inst", "incIngr", "excIngr", 10, true));
        verify(recipeSearchService).searchRecipes(searchArgumentCaptor.capture());
        assertEquals(1, searchArgumentCaptor.getAllValues().size());
        SearchQueryData searchQueryData = searchArgumentCaptor.getValue();
        assertEquals("inst", searchQueryData.getInstructions());
        assertEquals("incIngr", searchQueryData.getIncludeIngredient());
        assertEquals("excIngr", searchQueryData.getExcludeIngredient());
        assertEquals(10, searchQueryData.getServings());
        assertTrue(searchQueryData.getOnlyVeg());
    }

    @Test
    public void testSearchRecipes_NoIndex() {
        List<Recipe> recipeList = Collections.singletonList(new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipeList);
        assertEquals(recipeList, sut.searchRecipes(null, null, null, null, null));
    }
}
