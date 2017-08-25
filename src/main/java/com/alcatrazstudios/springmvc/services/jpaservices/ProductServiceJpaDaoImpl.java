package com.alcatrazstudios.springmvc.services.jpaservices;

import com.alcatrazstudios.springmvc.domain.Product;
import com.alcatrazstudios.springmvc.services.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by irepan on 13/07/17.
 */
@Service
@Profile("jpadao")
public class ProductServiceJpaDaoImpl extends AbstractJpaDaoService<Product> implements ProductService {

    @Override
    public List<Product> listAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from Product", Product.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Product getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Product savedProduct = em.merge(domainObject);

        em.getTransaction().commit();
        em.close();
        return savedProduct;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.remove(em.find(Product.class, id));

        em.getTransaction().commit();
        em.close();
    }

}
