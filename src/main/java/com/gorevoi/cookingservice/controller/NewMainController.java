package com.gorevoi.cookingservice.controller;

import com.gorevoi.cookingservice.dao.interfaces.RecipeDao;
import com.gorevoi.cookingservice.dao.interfaces.RoleDao;
import com.gorevoi.cookingservice.dao.interfaces.UserDao;
import com.gorevoi.cookingservice.model.Recipe;
import com.gorevoi.cookingservice.model.UserOfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewMainController {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final RecipeDao recipeDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public NewMainController(UserDao userDao, RoleDao roleDao,
                             RecipeDao recipeDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.recipeDao = recipeDao;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    public UserOfService userRegistration(@RequestParam String name,
                                         @RequestParam String login,
                                         @RequestParam String password)
    {
        UserOfService userOfService = new UserOfService();
        userOfService.setName(name);
        userOfService.setLogin(login);
        userOfService.setPassword(passwordEncoder.encode(password));
        userOfService.setRolesList(roleDao.findRoleById());
        return userDao.save(userOfService);

    }

    @GetMapping("/getUsers")
    public List<UserOfService> getUsers(){
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDao.findAll();
    }


    @PostMapping("/addRecipe")
    public Recipe addRecipe(
            @RequestParam(required = true) String title,
            @RequestParam(required = true) String link)
    {
        UserOfService user= (UserOfService) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Recipe recipe = new Recipe();
        recipe.setTitle(title);
        recipe.setLink(link);
        recipe.setUser(userDao.getUserOfServiceByLogin(user.getLogin()));
        return recipeDao.save(recipe);

    }

    @GetMapping("/getRecipes")
    public List<Recipe> getRecipes(){
        return recipeDao.findAll();
//        return recipeDao.getRecipeByUserId(1L);
    }

    @ExceptionHandler(Exception.class)
    public String handleExeption(Exception ex){
        return "WOW! "+ex.getLocalizedMessage();

    }
}
