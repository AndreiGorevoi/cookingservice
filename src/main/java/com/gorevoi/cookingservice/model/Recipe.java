package com.gorevoi.cookingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gorevoi.cookingservice.dto.recipe.InfoRecipeDto;
import com.gorevoi.cookingservice.dto.recipe.UpdateRecipeDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Recipe extends BaseModel {
    @Column
    private String title;

    @Column
    private String link;

    @Column
    private String description;

    @Column
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id",insertable = false, updatable = false)
//    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private User user;

    public static Recipe fromDto(UpdateRecipeDto dto) {
        Recipe recipe= new Recipe();
        recipe.setLink(dto.getLink());
        recipe.setTitle(dto.getTitle());
//        recipe.setDescription(dto.getDescription());
        recipe.setDescription("this is default recipe description");
//        recipe.setImg(dto.getImg());
        recipe.setImg("this is default image path");
        return recipe;
    }

    public static InfoRecipeDto fromRecipe(Recipe recipe) {
        InfoRecipeDto dto = new InfoRecipeDto();
        dto.setLink(recipe.getLink());
        dto.setTitle(recipe.getTitle());
        dto.setDescription(recipe.getDescription());
        dto.setImg(recipe.getImg());
        return dto;
    }

}
