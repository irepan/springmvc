package com.alcatrazstudios.springmvc.repositories;

import com.alcatrazstudios.springmvc.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Integer>{
}
