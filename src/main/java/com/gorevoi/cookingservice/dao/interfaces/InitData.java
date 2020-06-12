package com.gorevoi.cookingservice.dao.interfaces;

import com.gorevoi.cookingservice.model.ERoles;
import com.gorevoi.cookingservice.model.Role;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

@Repository
public class InitData implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        if(applicationEvent instanceof ContextRefreshedEvent){
            ApplicationContext context = ((ContextRefreshedEvent) applicationEvent).getApplicationContext();
            RoleDao roleDao = context.getBean(RoleDao.class);
            Role roleUser = new Role();
            Role roleAdmin = new Role();
            Role roleModerator = new Role();
            roleUser.setName(ERoles.USER);
            roleAdmin.setName(ERoles.ADMIN);
            roleModerator.setName(ERoles.MODERATOR);
            roleDao.save(roleUser);
            roleDao.save(roleAdmin);
            roleDao.save(roleModerator);

        }
    }
}
