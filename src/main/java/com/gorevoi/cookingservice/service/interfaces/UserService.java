package com.gorevoi.cookingservice.service.interfaces;

import com.gorevoi.cookingservice.model.User;

import java.util.List;

public interface UserService {
    User getUserOfServiceByLogin(String login);

    List<User> findAll();

    User save(User user);

    User findById(Long userId);
}
