package com.alcatrazstudios.springmvc.services.jpaservices;

import com.alcatrazstudios.springmvc.domain.DomainObject;
import com.alcatrazstudios.springmvc.services.CRUDService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.*;

/**
 * Created by irepan on 11/07/17.
 */
public abstract class AbstractJpaDaoService<T extends DomainObject> implements CRUDService<T> {
    protected EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

}
