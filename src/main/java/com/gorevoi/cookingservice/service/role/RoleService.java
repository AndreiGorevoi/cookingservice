package com.gorevoi.cookingservice.service.interfaces;

import com.gorevoi.cookingservice.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findRoleById();
    List<Role> findAllRoles();
}
