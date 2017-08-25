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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(JpaIntegrationConfig.class)@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testSaveOfUser() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getEncryptedPassword() != null;

        System.out.println("Encrypted Password");
        System.out.println(savedUser.getEncryptedPassword());

    }

    @Test
    public void testSaveOfUserWithCustomer() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

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

        user.setCustomer(customer);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getEncryptedPassword() != null;

        assert savedUser.getCustomer().getFirstName().equals("Irepan");
        assert savedUser.getCustomer().getLastName().equals("Chavez");
        assert savedUser.getCustomer().getBillingAddress().getAddressLine1().equals("Martha Dueñas 5442");
        assert savedUser.getCustomer().getBillingAddress().getCity().equals("Zapopan");
        assert savedUser.getCustomer().getBillingAddress().getState().equals("Jalisco");
        assert savedUser.getCustomer().getBillingAddress().getZipCode().equals("45079");
        assert savedUser.getCustomer().getEmail().equals("irepitan@gmail.com");
        assert savedUser.getCustomer().getPhoneNumber().equals("33 3159 2404");


    }
}