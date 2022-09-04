package com.rms.assignment.core.search.service;

import java.util.List;

import com.rms.assignment.core.entity.Recipe;
import com.rms.assignment.core.search.SearchQueryData;

public interface RecipeSearchService {
    List<Recipe> searchRecipes(SearchQueryData searchQueryData);
}
