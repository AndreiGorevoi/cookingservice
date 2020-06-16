package com.gorevoi.cookingservice.controller;

import com.gorevoi.cookingservice.dto.recipe.InfoRecipeDto;
import com.gorevoi.cookingservice.dto.recipe.UpdateRecipeDto;
import com.gorevoi.cookingservice.model.Recipe;
import com.gorevoi.cookingservice.model.User;
import com.gorevoi.cookingservice.service.interfaces.RecipeService;
import com.gorevoi.cookingservice.service.interfaces.RoleService;
import com.gorevoi.cookingservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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
    public User userRegistration(@RequestParam String name,
                                 @RequestParam String login,
                                 @RequestParam String password)
    {
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roleService.findRoleById());
        return userService.save(user);

    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userService.findAll();
    }

    @PostMapping("/addRecipe")
    public Recipe addRecipe(@RequestBody UpdateRecipeDto dto)
    {
        Recipe recipe = Recipe.fromDto(dto);
        User user = new User();
        user.setId(dto.getUserId());
        recipe.setUser(user);
        return recipeService.save(recipe);
    }

    @GetMapping("/getRecipes")
    public List<InfoRecipeDto> getRecipes(){
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Recipe> recipes = recipeService.getRecipeByUserId(user.getId());
        return recipes.stream().map(Recipe::fromRecipe).collect(Collectors.toList());
    }

    @ExceptionHandler(Exception.class)
    public String handleExeption(Exception ex){
        return "WOW! "+ex.getLocalizedMessage();

    }
}
