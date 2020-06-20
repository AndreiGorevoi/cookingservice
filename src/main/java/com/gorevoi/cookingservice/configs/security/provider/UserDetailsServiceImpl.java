package com.gorevoi.cookingservice.configs.security.provider;

import com.gorevoi.cookingservice.model.User;
import com.gorevoi.cookingservice.model.UserDetailsMyImpl;
import com.gorevoi.cookingservice.repository.role.RoleRepository;
import com.gorevoi.cookingservice.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getUserOfServiceByLogin(s);
        return  UserDetailsMyImpl.builder()
                .username(user.getName())
                .password(user.getPassword())
                .userId(user.getId())
                .roles(user.getRolesList().stream().map((it)->it.getName().toString()).collect(Collectors.joining()))
                .build();

    }
}
