package com.gorevoi.cookingservice.service;

import com.gorevoi.cookingservice.repository.RoleDao;
import com.gorevoi.cookingservice.model.Role;
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
