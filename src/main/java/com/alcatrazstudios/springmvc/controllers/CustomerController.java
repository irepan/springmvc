package com.alcatrazstudios.springmvc.controllers;

import com.alcatrazstudios.springmvc.commands.CustomerForm;
import com.alcatrazstudios.springmvc.domain.Customer;
import com.alcatrazstudios.springmvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by irepan on 11/07/17.
 */
@Controller
public class CustomerController {

    CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public String listCustomers(Model model){
        model.addAttribute("customers", customerService.listAll());
        return "/customer/list";
    }

    @RequestMapping("/customer/{id}")
    public String getCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getById(id));
        return "/customer/show";
    }

    @RequestMapping("/customer/new")
    public String newCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "/customer/customerform";
    }

/*    @RequestMapping(value="/customer",method= RequestMethod.POST)
    public String saveOrDelete(Customer customer){
        Customer savedCustomer=customerService.saveOrUpdate(customer);
        return "redirect:/customer/" + savedCustomer.getId();
    }
*/
    @RequestMapping(value="/customer",method = RequestMethod.POST)
    public String saveOrUpdate(CustomerForm customerForm){

        Customer newCustomer = customerService.saveOrUpdateCustomerForm(customerForm);
        return "redirect:/customer/" + newCustomer.getId();
    }

    @RequestMapping("/customer/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model){
        Customer customer = customerService.getById(id);
        CustomerForm customerForm = new CustomerForm();

        customerForm.setCustomerId(customer.getId());
        customerForm.setCustomerVersion(customer.getVersion());
        customerForm.setEmail(customer.getEmail());
        customerForm.setFirstName(customer.getFirstName());
        customerForm.setLastName(customer.getLastName());
        customerForm.setPhoneNumber(customer.getPhoneNumber());
        customerForm.setUserId(customer.getUser().getId());
        customerForm.setUserName(customer.getUser().getUsername());
        customerForm.setUserVersion(customer.getUser().getVersion());
        model.addAttribute("customer", customerForm);

        return "/customer/customerform";
    }

    @RequestMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id){
        customerService.delete(id);
        return "redirect:/customers";
    }
}
