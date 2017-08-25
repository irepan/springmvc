package com.alcatrazstudios.springmvc.services.jpaservices;

import com.alcatrazstudios.springmvc.domain.security.Role;
import com.alcatrazstudios.springmvc.services.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class RoleServiceJpaImpl extends AbstractJpaDaoService<Role> implements RoleService {

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from Role", Role.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Role getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Role.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Role saveRole = null;
        try {
            saveRole = em.merge(domainObject);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return saveRole;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        try {
            em.remove(em.find(Role.class, id));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}