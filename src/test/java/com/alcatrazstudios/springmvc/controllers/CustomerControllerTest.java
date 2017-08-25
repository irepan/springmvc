package com.alcatrazstudios.springmvc.controllers;

import com.alcatrazstudios.springmvc.domain.Address;
import com.alcatrazstudios.springmvc.domain.Customer;
import com.alcatrazstudios.springmvc.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.internal.matchers.InstanceOf;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Created by irepan on 12/07/17.
 */
public class CustomerControllerTest {
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setuo(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception{

        List<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerService.listAll()).thenReturn((List) customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)));


    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;

        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testEdit() throws Exception {

        Integer id = 1;

        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testNewCustomer() throws Exception {

        Integer id = 1;

        verifyZeroInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testSaveOrDelete() throws Exception {
        Integer id =1;
        String firstName="Michael";
        String lastName="Weston";
        String addressLine1 = "1 Main St";
        String city = "Miami";
        String state = "Florida";
        String zipCode = "33101";
        String email = "michael@burnnotice.com";
        String phoneNumber = "305.333.0101";

        Customer returnCustomer = new Customer();
        returnCustomer.setId(id);
        returnCustomer.setFirstName(firstName);
        returnCustomer.setLastName(lastName);
        returnCustomer.setShippingAddress(new Address());
        returnCustomer.getShippingAddress().setAddressLine1(addressLine1);
        returnCustomer.getShippingAddress().setCity(city);
        returnCustomer.getShippingAddress().setState(state);
        returnCustomer.getShippingAddress().setZipCode(zipCode);
        returnCustomer.setEmail(email);
        returnCustomer.setPhoneNumber(phoneNumber);

        when(customerService.saveOrUpdate(Matchers.<Customer>any())).thenReturn(returnCustomer);

        mockMvc.perform(post("/customer")
                .param("id", "1")
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("shippingAddress.addressLine1", addressLine1)
                .param("shippingAddress.city", city)
                .param("shippingAddress.state", state)
                .param("shippingAddress.zipCode", zipCode)
                .param("email", email)
                .param("phoneNumber", phoneNumber))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/1"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id",is(id))))
                .andExpect(model().attribute("customer", hasProperty("firstName",is(firstName))))
                .andExpect(model().attribute("customer", hasProperty("lastName",is(lastName))))
                .andExpect(model().attribute("customer", hasProperty("shippingAddress",hasProperty("addressLine1",is(addressLine1)))))
                .andExpect(model().attribute("customer", hasProperty("shippingAddress",hasProperty("city",is(city)))))
                .andExpect(model().attribute("customer", hasProperty("shippingAddress",hasProperty("state",is(state)))))
                .andExpect(model().attribute("customer", hasProperty("shippingAddress",hasProperty("zipCode",is(zipCode)))))
                .andExpect(model().attribute("customer", hasProperty("email",is(email))))
                .andExpect(model().attribute("customer", hasProperty("phoneNumber",is(phoneNumber))));

        ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdate(boundCustomer.capture());

        assertEquals(id, boundCustomer.getValue().getId());
        assertEquals(firstName, boundCustomer.getValue().getFirstName());
        assertEquals(lastName, boundCustomer.getValue().getLastName());
        assertEquals(addressLine1, boundCustomer.getValue().getShippingAddress().getAddressLine1());
        assertEquals(city, boundCustomer.getValue().getShippingAddress().getCity());
        assertEquals(state, boundCustomer.getValue().getShippingAddress().getState());
        assertEquals(zipCode, boundCustomer.getValue().getShippingAddress().getZipCode());
        assertEquals(email, boundCustomer.getValue().getEmail());
        assertEquals(phoneNumber, boundCustomer.getValue().getPhoneNumber());

    }
}
