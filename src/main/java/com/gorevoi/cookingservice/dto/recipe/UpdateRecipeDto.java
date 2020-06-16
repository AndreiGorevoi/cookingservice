package com.gorevoi.cookingservice.dto.recipe;

import lombok.Data;

@Data
public class UpdateRecipeDto {

    private Long userId;
    private String title;
    private String link;
    private String description;
    private String img;

}
