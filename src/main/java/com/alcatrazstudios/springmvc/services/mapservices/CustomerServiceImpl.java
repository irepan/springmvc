package com.alcatrazstudios.springmvc.services.mapservices;

import com.alcatrazstudios.springmvc.commands.CustomerForm;
import com.alcatrazstudios.springmvc.converters.CustomerFormToCustomer;
import com.alcatrazstudios.springmvc.domain.Address;
import com.alcatrazstudios.springmvc.domain.Customer;
import com.alcatrazstudios.springmvc.services.CustomerService;
import com.alcatrazstudios.springmvc.services.jpaservices.AbstractJpaDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by irepan on 11/07/17.
 */
@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService<Customer> implements CustomerService {

    private CustomerFormToCustomer customerFormToCustomer;

    @Autowired
    public void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer) {
        this.customerFormToCustomer = customerFormToCustomer;
    }

    @Override
    protected void loadDomainObjects() {
        domainMap = new HashMap<>();

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("Micheal");
        customer1.setLastName("Weston");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLine1("1 Main St");
        customer1.getBillingAddress().setCity("Miami");
        customer1.getBillingAddress().setState("Florida");
        customer1.getBillingAddress().setZipCode("33101");
        customer1.setEmail("micheal@burnnotice.com");
        customer1.setPhoneNumber("305.333.0101");

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("Fiona");
        customer2.setLastName("Glenanne");
        customer1.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressLine1("1 Key Biscane Ave");
        customer2.getBillingAddress().setCity("Miami");
        customer2.getBillingAddress().setState("Florida");
        customer2.getBillingAddress().setZipCode("33101");
        customer2.setEmail("fiona@burnnotice.com");
        customer2.setPhoneNumber("305.323.0233");

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setFirstName("Sam");
        customer3.setLastName("Axe");
        customer1.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddressLine1("1 Little Cuba Road");
        customer3.getBillingAddress().setCity("Miami");
        customer3.getBillingAddress().setState("Florida");
        customer3.getBillingAddress().setZipCode("33101");
        customer3.setEmail("sam@burnnotice.com");
        customer3.setPhoneNumber("305.426.9832");

        domainMap.put(1, customer1);
        domainMap.put(2, customer2);
        domainMap.put(3, customer3);
    }

    @Override
    public List<Customer> listAll() {
        return super.listAll();
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return (Customer) super.saveOrUpdate(domainObject);
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
