package com.gorevoi.cookingservice.repository.recipe;

import com.gorevoi.cookingservice.model.ETypeOfMealTime;
import com.gorevoi.cookingservice.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("from Recipe r where r.user.id=?1")
    List<Recipe> getRecipeByUserId(Long id);

    @Query("from Recipe r where r.user.id=?1 and r.mealTime=?2")
    List<Recipe> getRecipesByMealTime(Long id, ETypeOfMealTime mealType);

}
