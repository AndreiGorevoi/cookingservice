package com.gorevoi.cookingservice.controller;

import com.gorevoi.cookingservice.model.Recipe;
import com.gorevoi.cookingservice.model.UserOfService;
import com.gorevoi.cookingservice.service.interfaces.RecipeService;
import com.gorevoi.cookingservice.service.interfaces.RoleService;
import com.gorevoi.cookingservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewMainController {
    private final UserService userService;
    private final RoleService roleService;
    private final RecipeService recipeService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public NewMainController(UserService userService, RoleService roleService, RecipeService recipeService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.recipeService = recipeService;
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
        userOfService.setRolesList(roleService.findRoleById());
        return userService.save(userOfService);

    }

    @GetMapping("/getUsers")
    public List<UserOfService> getUsers(){
        return userService.findAll();
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
        recipe.setUser(userService.getUserOfServiceByLogin(user.getLogin()));
        return recipeService.save(recipe);

    }

    @GetMapping("/getRecipes")
    public List<Recipe> getRecipes(){
        UserOfService user= (UserOfService) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return recipeService.getRecipeByUserId(user.getId());
    }

    @ExceptionHandler(Exception.class)
    public String handleExeption(Exception ex){
        return "WOW! "+ex.getLocalizedMessage();

    }
}
