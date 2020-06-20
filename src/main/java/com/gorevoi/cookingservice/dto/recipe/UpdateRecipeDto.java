package com.gorevoi.cookingservice.dto.recipe;

import com.gorevoi.cookingservice.model.ETypeOfMealTime;
import com.gorevoi.cookingservice.model.Recipe;
import lombok.Data;

@Data
public class UpdateRecipeDto {
    private Long userId;
    private String title;
    private String link;
    private String img;
    private String description;
    private ETypeOfMealTime mealTime;

    public static Recipe updateRecipeFromDto(UpdateRecipeDto dto){
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.getTitle());
        recipe.setLink(dto.getLink());
        recipe.setImg(dto.getImg());
        recipe.setDescription(dto.getDescription());
        recipe.setMealTime(dto.getMealTime());
        return recipe;
    }
}
