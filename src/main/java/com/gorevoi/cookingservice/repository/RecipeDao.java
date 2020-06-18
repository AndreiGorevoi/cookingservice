package com.gorevoi.cookingservice.repository;

import com.gorevoi.cookingservice.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeDao extends JpaRepository<Recipe, Long> {
    @Query("from Recipe r where r.user.id = :id")
    List<Recipe> getRecipeByUserId(Long id);
}
