package com.gorevoi.cookingservice.service.impl;

import com.gorevoi.cookingservice.repository.role.RoleRepository;
import com.gorevoi.cookingservice.model.Role;
import com.gorevoi.cookingservice.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findRoleById() {
        return roleRepository.findRoleById();
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
