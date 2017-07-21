package com.alcatrazstudios.springmvc.bootstrap;

import com.alcatrazstudios.springmvc.domain.Customer;
import com.alcatrazstudios.springmvc.domain.Product;
import com.alcatrazstudios.springmvc.services.CustomerService;
import com.alcatrazstudios.springmvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by irepan on 13/07/17.
 */
@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadCustomers();
    }

    private void loadProducts(){
        Product newProduct = new Product();
        newProduct.setDescription("Product 1");
        newProduct.setPrice(new BigDecimal("15.02"));
        newProduct.setImageUrl("http://www.example.com/product1.jpg");
        productService.saveOrUpdate(newProduct);

        newProduct = new Product();
        newProduct.setDescription("Product 2");
        newProduct.setPrice(new BigDecimal("5.28"));
        newProduct.setImageUrl("http://www.example.com/product2.jpg");
        productService.saveOrUpdate(newProduct);

        newProduct = new Product();
        newProduct.setDescription("Product 3");
        newProduct.setPrice(new BigDecimal("329.50"));
        newProduct.setImageUrl("http://www.example.com/product3.jpg");
        productService.saveOrUpdate(newProduct);

        newProduct = new Product();
        newProduct.setDescription("Product 4");
        newProduct.setPrice(new BigDecimal("78.99"));
        newProduct.setImageUrl("http://www.example.com/product4.jpg");
        productService.saveOrUpdate(newProduct);

        newProduct = new Product();
        newProduct.setDescription("Product 5");
        newProduct.setPrice(new BigDecimal("65.45"));
        newProduct.setImageUrl("http://www.example.com/product5.jpg");
        productService.saveOrUpdate(newProduct);
    }

    private void loadCustomers(){
        Customer customer = new Customer();
        customer.setFirstName("Micheal");
        customer.setLastName("Weston");
        customer.setAddressLine1("1 Main St");
        customer.setCity("Miami");
        customer.setState("Florida");
        customer.setZipCode("33101");
        customer.setEmail("micheal@burnnotice.com");
        customer.setPhoneNumber("305.333.0101");
        customerService.saveOrUpdate(customer);

        customer = new Customer();
        customer.setFirstName("Fiona");
        customer.setLastName("Glenanne");
        customer.setAddressLine1("1 Key Biscane Ave");
        customer.setCity("Miami");
        customer.setState("Florida");
        customer.setZipCode("33101");
        customer.setEmail("fiona@burnnotice.com");
        customer.setPhoneNumber("305.323.0233");
        customerService.saveOrUpdate(customer);

        customer = new Customer();
        customer.setFirstName("Sam");
        customer.setLastName("Axe");
        customer.setAddressLine1("1 Little Cuba Road");
        customer.setCity("Miami");
        customer.setState("Florida");
        customer.setZipCode("33101");
        customer.setEmail("sam@burnnotice.com");
        customer.setPhoneNumber("305.426.9832");
        customerService.saveOrUpdate(customer);

    }
}
