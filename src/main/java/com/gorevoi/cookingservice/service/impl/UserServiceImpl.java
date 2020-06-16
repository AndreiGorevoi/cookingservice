package com.gorevoi.cookingservice.service.impl;

import com.gorevoi.cookingservice.dao.interfaces.UserRepository;
import com.gorevoi.cookingservice.model.User;
import com.gorevoi.cookingservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Autowired
    public UserServiceImpl(UserRepository user_dao) {
        repo = user_dao;
    }

    @Override
    public User getUserOfServiceByLogin(String login) {
        return repo.getUserOfServiceByLogin(login);
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User save(User user) {
        User checkUser= repo.getUserOfServiceByLogin(user.getLogin());
        if(checkUser==null){
            return repo.save(user);
        }
        return null;
    }

    @Override
    public User findById(Long userId) {
        return repo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User with id [%s] not exists", userId)));
    }
}
