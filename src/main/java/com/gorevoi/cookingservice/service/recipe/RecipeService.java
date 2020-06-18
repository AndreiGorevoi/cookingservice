package com.gorevoi.cookingservice.service.recipe;

import com.gorevoi.cookingservice.model.Recipe;

import java.util.List;

public interface RecipeService {

    Recipe save(Recipe recipe);
    List<Recipe> findAllRecipe();
    List<Recipe> getRecipeByUserId(Long id);
}
