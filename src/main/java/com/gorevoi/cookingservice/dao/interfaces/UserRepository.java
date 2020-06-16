package com.gorevoi.cookingservice.dao.interfaces;

import com.gorevoi.cookingservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User getUserOfServiceByLogin(String login);

}

