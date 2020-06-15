package com.gorevoi.cookingservice.service.interfaces;

import com.gorevoi.cookingservice.model.UserOfService;

import java.util.List;

public interface UserService {
    UserOfService getUserOfServiceByLogin(String login);

    List<UserOfService> findAll();

    UserOfService save(UserOfService user);
}
