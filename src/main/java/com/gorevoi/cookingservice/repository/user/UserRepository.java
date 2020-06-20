package com.gorevoi.cookingservice.repository.user;

import com.gorevoi.cookingservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     @Query("from User u where u.login=?1")
     User getUserOfServiceByLogin(String login);

     @Query("from User u where u.id=?1")
     User findUserById(Long id);

}

