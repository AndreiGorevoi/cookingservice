package com.gorevoi.cookingservice.repository;

import com.gorevoi.cookingservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    @Query("from Role r where r.id=1L")
    List<Role> findRoleById();
}
