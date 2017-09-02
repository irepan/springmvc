package com.alcatrazstudios.springmvc.bootstrap;

import com.alcatrazstudios.springmvc.domain.*;
import com.alcatrazstudios.springmvc.domain.security.Role;
import com.alcatrazstudios.springmvc.enums.OrderStatus;
import com.alcatrazstudios.springmvc.services.CustomerService;
import com.alcatrazstudios.springmvc.services.ProductService;
import com.alcatrazstudios.springmvc.services.RoleService;
import com.alcatrazstudios.springmvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by irepan on 13/07/17.
 */
@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadUsersAndCustomers();
        loadCarts();
        loadOrderHistory();
        loadRoles();
        assignUsersToDefaultRole();
    }

    private void loadOrderHistory() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user ->{
            Order order = new Order();
            order.setCustomer(user.getCustomer());
            order.setOrderStatus(OrderStatus.SHIPPED);

            products.forEach(product -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addToOrderDetails(orderDetail);
            });
        });
    }

    private void loadCarts() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartDetail(cartDetail);
            userService.saveOrUpdate(user);
        });
    }

    private void assignUsersToDefaultRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role ->{
            if(role.getRole().equalsIgnoreCase("CUSTOMER")){
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }
        });
    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.saveOrUpdate(role);

        role = new Role();
        role.setRole("ADMIN");
        roleService.saveOrUpdate(role);

        role = new Role();
        role.setRole("SUPERADMIN");
        roleService.saveOrUpdate(role);
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

    private void loadUsersAndCustomers(){
        User user = new User();
        user.setUsername("mweston");
        user.setPassword("password");

        Customer customer = new Customer();
        customer.setFirstName("Micheal");
        customer.setLastName("Weston");
        customer.setBillingAddress(new Address());
        customer.getBillingAddress().setAddressLine1("1 Main St");
        customer.getBillingAddress().setCity("Miami");
        customer.getBillingAddress().setState("Florida");
        customer.getBillingAddress().setZipCode("33101");
        customer.setEmail("micheal@burnnotice.com");
        customer.setPhoneNumber("305.333.0101");
        customer.setUser(user);
        user.setCustomer(customer);
        userService.saveOrUpdate(user);

        user = new User();
        user.setUsername("fglenanne");
        user.setPassword("password");

        customer = new Customer();
        customer.setFirstName("Fiona");
        customer.setLastName("Glenanne");
        customer.setBillingAddress(new Address());
        customer.getBillingAddress().setAddressLine1("1 Key Biscane Ave");
        customer.getBillingAddress().setCity("Miami");
        customer.getBillingAddress().setState("Florida");
        customer.getBillingAddress().setZipCode("33101");
        customer.setEmail("fiona@burnnotice.com");
        customer.setPhoneNumber("305.323.0233");
        customer.setUser(user);
        user.setCustomer(customer);
        userService.saveOrUpdate(user);

        user = new User();
        user.setUsername("saxe");
        user.setPassword("password");

        customer = new Customer();
        customer.setFirstName("Sam");
        customer.setLastName("Axe");
        customer.setBillingAddress(new Address());
        customer.getBillingAddress().setAddressLine1("1 Little Cuba Road");
        customer.getBillingAddress().setCity("Miami");
        customer.getBillingAddress().setState("Florida");
        customer.getBillingAddress().setZipCode("33101");
        customer.setEmail("sam@burnnotice.com");
        customer.setPhoneNumber("305.426.9832");
        customer.setUser(user);
        user.setCustomer(customer);
        userService.saveOrUpdate(user);

    }
}
