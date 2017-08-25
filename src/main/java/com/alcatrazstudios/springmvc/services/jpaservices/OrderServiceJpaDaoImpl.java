package com.alcatrazstudios.springmvc.services.jpaservices;


import com.alcatrazstudios.springmvc.domain.Order;
import com.alcatrazstudios.springmvc.services.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class OrderServiceJpaDaoImpl extends AbstractJpaDaoService<Order> implements OrderService {
    @Override
    public List<Order> listAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from Order").getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Order saveOrUpdate(Order order) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Order savedOrder = em.merge(order);
        em.getTransaction().commit();
        em.close();
        return savedOrder;
    }

    @Override
    public Order getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Order.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Order.class,id));
        em.getTransaction().commit();
        em.close();
    }
}
