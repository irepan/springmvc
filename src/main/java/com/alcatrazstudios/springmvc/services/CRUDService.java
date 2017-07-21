package com.alcatrazstudios.springmvc.services;

import com.alcatrazstudios.springmvc.domain.DomainObject;

import java.util.List;

/**
 * Created by irepan on 11/07/17.
 */
public interface CRUDService<T extends DomainObject> {
    public List<?> listAll();
    public T saveOrUpdate(T domainObject);
    public T getById(Integer id);
    public void delete(Integer id);
}
