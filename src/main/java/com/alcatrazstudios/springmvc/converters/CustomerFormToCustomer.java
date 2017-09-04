package com.alcatrazstudios.springmvc.converters;

import com.alcatrazstudios.springmvc.commands.CustomerForm;
import com.alcatrazstudios.springmvc.domain.Address;
import com.alcatrazstudios.springmvc.domain.Customer;
import com.alcatrazstudios.springmvc.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerFormToCustomer extends DaoConverter<Customer, CustomerForm> {

    @Override
    public Customer convertToDao(Customer customer, CustomerForm customerForm) {
        if (customer.getUser() == null) {
            customer.setUser(new User());
        }
        if (customer.getBillingAddress() == null) {
            customer.setBillingAddress(new Address());
        }
        if (customer.getShippingAddress() == null) {
            customer.setShippingAddress(new Address());
        }
        customer.getUser().setId(customerForm.getUserId());
        customer.getUser().setVersion(customerForm.getUserVersion());
        customer.setId(customerForm.getCustomerId());
        customer.setVersion(customerForm.getCustomerVersion());
        customer.getUser().setUsername(customerForm.getUserName());
        customer.getUser().setPassword(customerForm.getPasswordText());
        customer.setFirstName(customerForm.getFirstName());
        customer.setLastName(customerForm.getLastName());
        customer.setEmail(customerForm.getEmail());
        customer.setPhoneNumber(customerForm.getPhoneNumber());

        return customer;
    }

    @Override
    public CustomerForm convertFromDao(CustomerForm customerForm, Customer customer) {
        customerForm.setCustomerId(customer.getId());
        customerForm.setCustomerVersion(customer.getVersion());
        customerForm.setUserId(customer.getUser().getId());
        customerForm.setUserVersion(customer.getVersion());
        customerForm.setUserName(customer.getUser().getUsername());
        customerForm.setPasswordText(customer.getUser().getPassword());
        customerForm.setFirstName(customer.getFirstName());
        customerForm.setLastName(customer.getLastName());
        customerForm.setEmail(customer.getEmail());
        customerForm.setPhoneNumber(customer.getPhoneNumber());
        return customerForm;
    }
}