package com.gorevoi.cookingservice.configs.security.provider;

import com.gorevoi.cookingservice.repository.RoleDao;
import com.gorevoi.cookingservice.repository.UserRepository;
import com.gorevoi.cookingservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleDao roleDao;

    @Autowired
    public AppAuthenticationProvider(UserDetailsService userDetailsService,
                                     UserRepository userRepository,
                                     PasswordEncoder passwordEncoder, RoleDao roleDao) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userRepository.getUserOfServiceByLogin(authentication.getName());
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        String password = authentication.getCredentials().toString();
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }
        List<SimpleGrantedAuthority> authorityList = user.getRoles().stream()
                .map((it->new SimpleGrantedAuthority("ROLE_"+it.getName()))).collect(Collectors.toList());

        //      Положил в сессию имя пользователя
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpSession session = attr.getRequest().getSession(false);
//        session.setAttribute("att",user.getName());
//
//
//        HttpServletRequest request = (HttpServletRequest) RequestContextHolder.getRequestAttributes();
//        session = request.getSession();
//        session.setAttribute("userId",user.getId());

        return new UsernamePasswordAuthenticationToken(user,null,authorityList);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
