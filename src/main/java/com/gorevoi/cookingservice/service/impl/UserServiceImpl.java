package com.gorevoi.cookingservice.service.impl;

import com.gorevoi.cookingservice.dao.interfaces.UserDao;
import com.gorevoi.cookingservice.model.UserOfService;
import com.gorevoi.cookingservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao USER_DAO;

    @Autowired
    public UserServiceImpl(UserDao user_dao) {
        USER_DAO = user_dao;
    }

    @Override
    public UserOfService getUserOfServiceByLogin(String login) {
        return USER_DAO.getUserOfServiceByLogin(login);
    }

    @Override
    public List<UserOfService> findAll() {
        return USER_DAO.findAll();
    }

    @Override
    public UserOfService save(UserOfService user) {
        UserOfService checkUser=USER_DAO.getUserOfServiceByLogin(user.getLogin());
        if(checkUser==null){
            return USER_DAO.save(user);
        }
        return null;
    }
}
