package com.gorevoi.cookingservice.provider;

import com.gorevoi.cookingservice.dao.interfaces.RoleDao;
import com.gorevoi.cookingservice.dao.interfaces.UserDao;
import com.gorevoi.cookingservice.model.UserOfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppAuthenticationProvider implements AuthenticationProvider {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final RoleDao roleDao;

    @Autowired
    public AppAuthenticationProvider(UserDao userDao, PasswordEncoder passwordEncoder, RoleDao roleDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserOfService user = userDao.getUserOfServiceByLogin(authentication.getName());
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        user.setRolesList(roleDao.findRoleById());
        String password = authentication.getCredentials().toString();
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }
        List<SimpleGrantedAuthority> authorityList = user.getRolesList().stream()
                .map((it->new SimpleGrantedAuthority("ROLE_"+it.getName()))).collect(Collectors.toList());

        //      Положил в сессию имя пользователя
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false);
        session.setAttribute("att",user.getName());

        return new UsernamePasswordAuthenticationToken(user,null,authorityList);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
