package com.rms.assignment.core.search.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.assignment.core.entity.Recipe;
import com.rms.assignment.core.repository.RecipeSearchRepository;
import com.rms.assignment.core.search.SearchQueryData;
import com.rms.assignment.core.search.service.RecipeSearchService;

/**
 * Search service used to search {@link Recipe}s using Search Index
 * 
 * @author Rohit
 *
 */
@Service
public class RecipeSearchServiceImpl implements RecipeSearchService {

    @Autowired
    private RecipeSearchRepository recipeSearchRepository;

    @Override
    public List<Recipe> searchRecipes(SearchQueryData searchQueryData) {
        return recipeSearchRepository.searchRecipes(searchQueryData);
    }

}
