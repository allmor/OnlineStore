package com.onlinestoreapp.services;

import com.onlinestoreapp.models.Role;
import com.onlinestoreapp.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    private final RoleRepository roleRepository;

    @Autowired
    public RolesService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role getDefaultRole() {
        return roleRepository.findByRoleName("ROLE_USER");
    }
}
