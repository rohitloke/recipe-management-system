package com.rms.assignment.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.rms.assignment.api.dto.RecipeDto;
import com.rms.assignment.core.entity.Recipe;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RecipeDtoMapper {

    RecipeDto toRecipeDto(Recipe recipe);

    Recipe toRecipe(RecipeDto RecipeDto);
}
