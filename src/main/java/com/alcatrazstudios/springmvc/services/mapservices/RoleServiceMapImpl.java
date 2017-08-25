package com.alcatrazstudios.springmvc.services.mapservices;

import com.alcatrazstudios.springmvc.domain.DomainObject;
import com.alcatrazstudios.springmvc.domain.security.Role;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("map")
public class RoleServiceMapImpl extends AbstractMapService<Role> {

    @Override
    public List<Role> listAll() {
        return super.listAll();
    }

    @Override
    protected void loadDomainObjects() {
        Role role = new Role();
        role.setRole("CUSTOMER");
        domainMap.put(1, role);

        role = new Role();
        role.setRole("ADMIN");
        domainMap.put(2, role);


        role = new Role();
        role.setRole("MANAGER");
        domainMap.put(3, role);
    }
}