package com.alcatrazstudios.springmvc.services.jpaservices;

import com.alcatrazstudios.springmvc.commands.CustomerForm;
import com.alcatrazstudios.springmvc.converters.CustomerFormToCustomer;
import com.alcatrazstudios.springmvc.domain.Customer;
import com.alcatrazstudios.springmvc.services.CustomerService;
import com.alcatrazstudios.springmvc.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CustomerServiceJpaDaoImpl extends AbstractJpaDaoService<Customer> implements CustomerService {

    private EncryptionService encryptionService;
    private CustomerFormToCustomer customerFormToCustomer;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Autowired
    public void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer) {
        this.customerFormToCustomer = customerFormToCustomer;
    }

    @Override
    public List<Customer> listAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from Customer").getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        if (customer.getUser() != null && customer.getUser().getPassword() != null) {
            customer.getUser().setEncryptedPassword(
                    encryptionService.encryptString(customer.getUser().getPassword()));
        }

        Customer savedCustomer = em.merge(customer);
        em.getTransaction().commit();
        em.close();
        return savedCustomer;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Customer.class,id));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Customer saveOrUpdateCustomerForm(CustomerForm customerForm) {
        Customer updatedCustomer = null;
        if (customerForm.getUserId() != null){
            updatedCustomer = customerFormToCustomer.convertToDao(getById(customerForm.getCustomerId()), customerForm);
        } else {
            updatedCustomer = customerFormToCustomer.convertToDao(customerForm);
        }
        return saveOrUpdate(updatedCustomer);
    }

}
