package com.rms.assignment.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rms.assignment.api.dto.RecipeDto;
import com.rms.assignment.api.dto.mapper.RecipeDtoMapper;
import com.rms.assignment.core.entity.Recipe;
import com.rms.assignment.core.service.RecipeService;

@RestController
@RequestMapping("recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeDtoMapper recipeMapper;

    @GetMapping
    public List<RecipeDto> getAll() {
        return recipeService.getAll().stream().map(recipeMapper::toRecipeDto).collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    public RecipeDto getById(@PathVariable String id) {
        Optional<Recipe> recipe = recipeService.getById(Long.parseLong(id));
        return recipe.isPresent() ? recipeMapper.toRecipeDto(recipe.get()) : null;
    }

    @GetMapping(path = "/search")
    public List<RecipeDto> search(@RequestParam(required = false) String instructions,
            @RequestParam(required = false) String includeIngredients,
            @RequestParam(required = false) String excludeIngredients, @RequestParam(required = false) Integer servings,
            @RequestParam(required = false) Boolean onlyVeg) {
        List<Recipe> recipes = recipeService.searchRecipes(instructions, includeIngredients, excludeIngredients,
                servings, onlyVeg);
        return recipes.stream().map(recipeMapper::toRecipeDto).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeDto> create(@RequestBody RecipeDto recipeDto) {
        return new ResponseEntity<RecipeDto>(
                recipeMapper.toRecipeDto(recipeService.create(recipeMapper.toRecipe(recipeDto))), HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeDto> update(@RequestBody RecipeDto recipeDto) {
        return new ResponseEntity<RecipeDto>(
                recipeMapper.toRecipeDto(recipeService.update(recipeMapper.toRecipe(recipeDto))), HttpStatus.OK);
    }

}
