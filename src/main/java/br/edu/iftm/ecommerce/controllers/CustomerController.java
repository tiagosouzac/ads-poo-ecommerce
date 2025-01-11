package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    public List<Customer> getCustomers() {
        return this.customerService.findAll();
    }

    public void saveCustomer(Customer customer) {
        this.customerService.save(customer);
    }

    public void deleteCustomer(Customer customer) {
        this.customerService.delete(customer);
    }
}
