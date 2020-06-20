package com.gorevoi.cookingservice.service.user;

import com.gorevoi.cookingservice.repository.user.UserRepository;
import com.gorevoi.cookingservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository user_dao) {
        userRepository = user_dao;
    }

    @Override
    public User getUserOfServiceByLogin(String login) {
        return userRepository.getUserOfServiceByLogin(login);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        User checkUser= userRepository.getUserOfServiceByLogin(user.getLogin());
        if(checkUser==null){
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }
}
