package com.gorevoi.cookingservice.dao.interfaces;

import com.gorevoi.cookingservice.model.UserOfService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserOfService, Long> {
     UserOfService getUserOfServiceByLogin(String login);

}

