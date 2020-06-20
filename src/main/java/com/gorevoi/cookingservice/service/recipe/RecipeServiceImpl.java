package com.gorevoi.cookingservice.service.impl;

import com.gorevoi.cookingservice.repository.recipe.RecipeRepository;
import com.gorevoi.cookingservice.repository.user.UserRepository;
import com.gorevoi.cookingservice.model.ETypeOfMealTime;
import com.gorevoi.cookingservice.model.Recipe;
import com.gorevoi.cookingservice.service.interfaces.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository RECIPE_DAO;
    private final UserRepository USER_DAO;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipe_dao, UserRepository user_dao) {
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
        return recipeList;
    }

    @Override
    public List<Recipe> getMenuForDay(Long id) {
        Random random = new Random();
        List<Recipe> breakfastRecipeList = RECIPE_DAO.getRecipesByMealTime(id, ETypeOfMealTime.BREAKFAST);
        List<Recipe> dinnerRecipeList = RECIPE_DAO.getRecipesByMealTime(id, ETypeOfMealTime.DINNER);
        List<Recipe> supperRecipeList = RECIPE_DAO.getRecipesByMealTime(id, ETypeOfMealTime.SUPPER);
//        Filling of random menu
        List<Recipe> menu = new ArrayList<>(3);
        menu.add(breakfastRecipeList.get(random.nextInt(breakfastRecipeList.size())));
        menu.add(dinnerRecipeList.get(random.nextInt(dinnerRecipeList.size())));
        menu.add(supperRecipeList.get(random.nextInt(supperRecipeList.size())));

        return menu;
    }


}
