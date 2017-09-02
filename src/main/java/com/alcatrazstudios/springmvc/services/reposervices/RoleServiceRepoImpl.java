package com.alcatrazstudios.springmvc.services.reposervices;

import com.alcatrazstudios.springmvc.domain.security.Role;
import com.alcatrazstudios.springmvc.repositories.RoleRepository;
import com.alcatrazstudios.springmvc.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("springdatajpa")
public class RoleServiceRepoImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> listAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role saveOrUpdate(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getById(Integer id) {
        Optional<Role> role;
        role = roleRepository.findById(id);
        return role.isPresent()?role.get():null;
    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }
}
