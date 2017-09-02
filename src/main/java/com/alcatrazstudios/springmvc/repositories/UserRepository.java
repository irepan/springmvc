package com.alcatrazstudios.springmvc.repositories;

import com.alcatrazstudios.springmvc.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{
}
