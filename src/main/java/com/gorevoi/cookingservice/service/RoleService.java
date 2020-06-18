package com.gorevoi.cookingservice.service;

import com.gorevoi.cookingservice.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findRoleById();
    List<Role> findAllRoles();
}
