package com.gorevoi.cookingservice.dto.recipe;

import com.gorevoi.cookingservice.model.ETypeOfMealTime;
import com.gorevoi.cookingservice.model.Recipe;
import lombok.Data;

@Data
public class InfoRecipeDto {
    private Long userId;
    private String title;
    private String link;
    private String img;
    private String description;
    private ETypeOfMealTime mealTime;

    public static InfoRecipeDto getInfoRecipeDto(Recipe recipe){
        InfoRecipeDto recipeDto = new InfoRecipeDto();
        recipeDto.setTitle(recipe.getTitle());
        recipeDto.setLink(recipe.getLink());
        recipeDto.setImg(recipe.getImg());
        recipeDto.setDescription(recipe.getDescription());
        recipeDto.setMealTime(recipe.getMealTime());
        return recipeDto;
    }
}
