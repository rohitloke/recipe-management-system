package com.rms.assignment.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rms.assignment.core.entity.Recipe;

/**
 * JPA repository to manage {@link Recipe} entity in the database.
 * 
 * @author Rohit
 */
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
