package com.gorevoi.cookingservice.dao.interfaces;

import com.gorevoi.cookingservice.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RecipeDao extends JpaRepository<Recipe, Long> {
    @Query("from Recipe r where r.user.id=?1")
    List<Recipe> getRecipeByUserId(Long id);
}
