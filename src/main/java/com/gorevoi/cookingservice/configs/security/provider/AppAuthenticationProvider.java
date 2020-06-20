package com.gorevoi.cookingservice.configs.security.provider;


import com.gorevoi.cookingservice.model.UserDetailsMyImpl;
import com.gorevoi.cookingservice.service.interfaces.RoleService;
import com.gorevoi.cookingservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppAuthenticationProvider(UserService userService,
                                     RoleService roleService,
                                     @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                                     PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetailsMyImpl user = (UserDetailsMyImpl) userDetailsService.loadUserByUsername(authentication.getName());
        System.out.println(user.getUserId());

        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        String password = authentication.getCredentials().toString();
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }

        List<SimpleGrantedAuthority> authorityList = user.getAuthorities().stream()
                .map((it->new SimpleGrantedAuthority(it.getAuthority()))).collect(Collectors.toList());


        return new UsernamePasswordAuthenticationToken(user,null,authorityList);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
