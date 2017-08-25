package com.alcatrazstudios.springmvc.services;

import com.alcatrazstudios.springmvc.config.JpaIntegrationConfig;
import com.alcatrazstudios.springmvc.domain.Address;
import com.alcatrazstudios.springmvc.domain.Customer;
import com.alcatrazstudios.springmvc.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by irepan on 13/07/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class CustomerServiceJpaDaoImplTest {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void testListMethod() throws Exception{
        List<Customer> customers = (List<Customer>) customerService.listAll();

        assert customers.size() == 3;
    }

    @Test
    public void testGetByIdMethod() throws Exception {
        Customer customer = customerService.getById(1);
        assert customer != null;
        assert customer.getId() == 1;
        assert customer.getFirstName().equals("Micheal");
        assert customer.getLastName().equals("Weston");
        assert customer.getBillingAddress().getAddressLine1().equals("1 Main St");
        assert customer.getBillingAddress().getCity().equals("Miami");
        assert customer.getBillingAddress().getState().equals("Florida");
        assert customer.getBillingAddress().getZipCode().equals("33101");
        assert customer.getEmail().equals("micheal@burnnotice.com");
        assert customer.getPhoneNumber().equals("305.333.0101");
    }

    @Test
    public void testNewProduct() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Irepan");
        customer.setLastName("Chavez");
        customer.setBillingAddress(new Address());
        customer.getBillingAddress().setAddressLine1("Martha Dueñas 5442");
        customer.getBillingAddress().setCity("Zapopan");
        customer.getBillingAddress().setState("Jalisco");
        customer.getBillingAddress().setZipCode("45079");
        customer.setEmail("irepitan@gmail.com");
        customer.setPhoneNumber("33 3159 2404");

        Customer newCustomer = customerService.saveOrUpdate(customer);

        assert newCustomer.getId() != null && newCustomer.getId() > 0;
        assert newCustomer.getFirstName().equals("Irepan");
        assert newCustomer.getLastName().equals("Chavez");
        assert newCustomer.getBillingAddress().getAddressLine1().equals("Martha Dueñas 5442");
        assert newCustomer.getBillingAddress().getCity().equals("Zapopan");
        assert newCustomer.getBillingAddress().getState().equals("Jalisco");
        assert newCustomer.getBillingAddress().getZipCode().equals("45079");
        assert newCustomer.getEmail().equals("irepitan@gmail.com");
        assert newCustomer.getPhoneNumber().equals("33 3159 2404");

    }

    @Test
    public void testUpdateProduct() throws Exception {
        Customer customer = customerService.getById(1);
        customer.setFirstName("Irepan");
        customer.setLastName("Chavez");
        customer.setBillingAddress(new Address());
        customer.getBillingAddress().setAddressLine1("Martha Dueñas 5442");
        customer.getBillingAddress().setCity("Zapopan");
        customer.getBillingAddress().setState("Jalisco");
        customer.getBillingAddress().setZipCode("45079");
        customer.setEmail("irepitan@gmail.com");
        customer.setPhoneNumber("33 3159 2404");

        Customer updatedCustomer = customerService.saveOrUpdate(customer);

        assert updatedCustomer.getId() == 1;
        assert updatedCustomer.getFirstName().equals("Irepan");
        assert updatedCustomer.getLastName().equals("Chavez");
        assert updatedCustomer.getBillingAddress().getAddressLine1().equals("Martha Dueñas 5442");
        assert updatedCustomer.getBillingAddress().getCity().equals("Zapopan");
        assert updatedCustomer.getBillingAddress().getState().equals("Jalisco");
        assert updatedCustomer.getBillingAddress().getZipCode().equals("45079");
        assert updatedCustomer.getEmail().equals("irepitan@gmail.com");
        assert updatedCustomer.getPhoneNumber().equals("33 3159 2404");

    }

    @Test
    public void testSaveWithUser() {

        Customer customer = new Customer();
        customer.setFirstName("Irepan");
        customer.setLastName("Chavez");
        customer.setBillingAddress(new Address());
        customer.getBillingAddress().setAddressLine1("Martha Dueñas 5442");
        customer.getBillingAddress().setCity("Zapopan");
        customer.getBillingAddress().setState("Jalisco");
        customer.getBillingAddress().setZipCode("45079");
        customer.setEmail("irepitan@gmail.com");
        customer.setPhoneNumber("33 3159 2404");

        User user = new User();
        user.setUsername("This is my user name");
        user.setPassword("MyAwesomePassword");
        customer.setUser(user);

        Customer savedCustomer = customerService.saveOrUpdate(customer);

        assert savedCustomer.getUser().getId() != null;
    }
}
