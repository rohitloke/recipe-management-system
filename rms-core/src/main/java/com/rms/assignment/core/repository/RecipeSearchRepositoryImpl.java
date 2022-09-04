package com.rms.assignment.core.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import com.rms.assignment.core.entity.Recipe;
import com.rms.assignment.core.search.SearchQueryData;

/**
 * This repository provides full text search for {@link Recipe} entity.
 * 
 * @author Rohit
 */
@Repository
public class RecipeSearchRepositoryImpl implements RecipeSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Recipe> searchRecipes(SearchQueryData searchQuery) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Recipe.class)
                .get();
        BooleanJunction<?> booleanJunction = queryBuilder.bool();
        if (searchQuery.getOnlyVeg() != null) {
            booleanJunction = booleanJunction
                    .should(queryBuilder.simpleQueryString().onField(Recipe.VEG_INDEX_FIELD_NAME)
                            .matching(searchQuery.getOnlyVeg() ? "true" : "false").createQuery());
        }
        if (searchQuery.getExcludeIngredient() != null) {
            booleanJunction = booleanJunction.must(queryBuilder.keyword().onField(Recipe.INGREDIENTS_INDEX_FIELD_NAME)
                    .matching(searchQuery.getExcludeIngredient()).createQuery()).not();
        }
        if (searchQuery.getIncludeIngredient() != null) {
            booleanJunction = booleanJunction.must(queryBuilder.keyword().onField(Recipe.INGREDIENTS_INDEX_FIELD_NAME)
                    .matching(searchQuery.getIncludeIngredient()).createQuery());
        }
        if (searchQuery.getInstructions() != null) {
            booleanJunction = booleanJunction.must(queryBuilder.keyword().onField(Recipe.INSTRUCTIONS_INDEX_FIELD_NAME)
                    .matching(searchQuery.getInstructions()).createQuery());
        }
        if (searchQuery.getServings() > 0) {
            booleanJunction = booleanJunction.should(queryBuilder.range().onField(Recipe.SERVES_INDEX_FIELD_NAME)
                    .above(searchQuery.getServings()).createQuery());
        }
        return fullTextEntityManager.createFullTextQuery(booleanJunction.createQuery(), Recipe.class).getResultList();
    }

}
