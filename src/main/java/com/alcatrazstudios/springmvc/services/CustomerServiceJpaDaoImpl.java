package com.alcatrazstudios.springmvc.services;

import com.alcatrazstudios.springmvc.domain.Customer;
import com.alcatrazstudios.springmvc.domain.Product;
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
public class CustomerServiceJpaDaoImpl extends AbstractMapService<Customer> implements CustomerService {

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Customer> listAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from Customer").getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Customer savedCustomer = em.merge(customer);
        em.getTransaction().commit();
        em.close();
        return savedCustomer;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Customer.class, id));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    protected void loadDomainObjects() {

    }
}
