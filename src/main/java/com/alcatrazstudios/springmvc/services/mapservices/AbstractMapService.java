package com.alcatrazstudios.springmvc.services.mapservices;

import com.alcatrazstudios.springmvc.domain.DomainObject;
import com.alcatrazstudios.springmvc.services.CRUDService;

import java.util.*;

/**
 * Created by irepan on 11/07/17.
 */
public abstract class AbstractMapService<T extends DomainObject> implements CRUDService<T> {
    protected Map<Integer, T> domainMap;

    public AbstractMapService(){
        domainMap = new HashMap<Integer, T>();
        loadDomainObjects();
    }

    public List<T> listAll(){
        return new ArrayList<T>(domainMap.values());
    }

    public T getById(Integer id){
        return domainMap.get(id);
    }

    public T saveOrUpdate(T domainObject) {
        if (domainObject != null){
            if (domainObject.getId() == null){
                domainObject.setId(getNextKey());
            }
            return domainObject;
        } else {
            throw new RuntimeException("domainObject cannot be null");
        }
    }

    public void delete(Integer id){
        domainMap.remove(id);
    }

    private Integer getNextKey() {
        return Collections.max(domainMap.keySet()) + 1;
    }

    protected abstract void loadDomainObjects();
}
