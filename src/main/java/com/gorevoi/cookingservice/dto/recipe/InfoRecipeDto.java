package com.gorevoi.cookingservice.dto.recipe;

import lombok.Data;

@Data
public class InfoRecipeDto {

    private Long userId;
    private String title;
    private String link;
    private String description;
    private String img;

}
