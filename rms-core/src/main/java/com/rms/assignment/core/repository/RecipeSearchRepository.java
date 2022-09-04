package com.rms.assignment.core.repository;

import java.util.List;

import com.rms.assignment.core.entity.Recipe;
import com.rms.assignment.core.search.SearchQueryData;

public interface RecipeSearchRepository {

    List<Recipe> searchRecipes(SearchQueryData searchQuery);
}
