package com.alcatrazstudios.springmvc.services.mapservices;

import com.alcatrazstudios.springmvc.domain.User;
import com.alcatrazstudios.springmvc.domain.security.Role;
import com.alcatrazstudios.springmvc.services.UserService;
import com.alcatrazstudios.springmvc.services.jpaservices.AbstractJpaDaoService;

import java.util.List;

/**
 * Created by jt on 12/14/15.
 */
public class UserServiceImpl extends AbstractMapService<User> implements UserService {
    @Override
    protected void loadDomainObjects() {
    }

    @Override
    public List<User> listAll() {
        return super.listAll();
    }

    @Override
    public User getById(Integer id) {
        return (User) super.getById(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return (User) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}