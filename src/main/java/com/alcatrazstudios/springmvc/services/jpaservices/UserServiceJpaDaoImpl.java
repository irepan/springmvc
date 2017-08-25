package com.alcatrazstudios.springmvc.services.jpaservices;

import com.alcatrazstudios.springmvc.domain.User;
import com.alcatrazstudios.springmvc.services.UserService;
import com.alcatrazstudios.springmvc.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by irepan on 25/07/17.
 */
@Service
@Profile("jpadao")
public class UserServiceJpaDaoImpl extends AbstractJpaDaoService<User> implements UserService {

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<User> listAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from User").getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public User getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public User saveOrUpdate(User user) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        if(user.getPassword() != null){
            user.setEncryptedPassword(encryptionService.encryptString(user.getPassword()));
        }

        try {
            User saveduser = em.merge(user);
            em.getTransaction().commit();
            return saveduser;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        try {
            em.remove(em.find(User.class, id));
            em.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}