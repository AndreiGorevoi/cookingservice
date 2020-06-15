package com.gorevoi.cookingservice.service.impl;

import com.gorevoi.cookingservice.dao.interfaces.RoleDao;
import com.gorevoi.cookingservice.model.Role;
import com.gorevoi.cookingservice.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> findRoleById() {
        return roleDao.findRoleById();
    }

    @Override
    public List<Role> findAllRoles() {
        return roleDao.findAll();
    }
}
