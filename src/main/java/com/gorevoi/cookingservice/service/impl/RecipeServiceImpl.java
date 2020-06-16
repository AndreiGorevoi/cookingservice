package com.gorevoi.cookingservice.service.impl;

import com.gorevoi.cookingservice.dao.interfaces.RecipeDao;
import com.gorevoi.cookingservice.dao.interfaces.UserRepository;
import com.gorevoi.cookingservice.model.Recipe;
import com.gorevoi.cookingservice.service.interfaces.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private final RecipeDao RECIPE_DAO;
    private final UserRepository USER_DAO;

    @Autowired
    public RecipeServiceImpl(RecipeDao recipe_dao, UserRepository user_dao) {
        RECIPE_DAO = recipe_dao;
        USER_DAO = user_dao;
    }

    @Override
    public Recipe save(Recipe recipe) {
        return RECIPE_DAO.save(recipe);
    }

    @Override
    public List<Recipe> findAllRecipe() {
        return RECIPE_DAO.findAll();
    }

    @Override
    public List<Recipe> getRecipeByUserId(Long id) {
        List<Recipe> recipeList = RECIPE_DAO.getRecipeByUserId(id);
//        for (Recipe recipe: recipeList) {
//            recipe.setUser(USER_DAO.getUserOfServiceByLogin("andrei"));
//        }
        return recipeList;
    }
}
