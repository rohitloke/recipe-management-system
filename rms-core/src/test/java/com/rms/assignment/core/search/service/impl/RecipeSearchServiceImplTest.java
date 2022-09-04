package com.rms.assignment.core.search.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rms.assignment.core.entity.Recipe;
import com.rms.assignment.core.repository.RecipeSearchRepository;
import com.rms.assignment.core.search.SearchQueryData;

@ExtendWith(MockitoExtension.class)
public class RecipeSearchServiceImplTest {
    @Mock
    private RecipeSearchRepository recipeSearchRepository;

    @InjectMocks
    private RecipeSearchServiceImpl sut;

    @Test
    public void testSearchRecipes() {
        SearchQueryData searchQuery = new SearchQueryData(null, 0, null, null, null);
        List<Recipe> recipeList = Collections.singletonList(new Recipe());
        when(recipeSearchRepository.searchRecipes(searchQuery)).thenReturn(recipeList);

        assertEquals(recipeList, sut.searchRecipes(searchQuery));
    }

}
